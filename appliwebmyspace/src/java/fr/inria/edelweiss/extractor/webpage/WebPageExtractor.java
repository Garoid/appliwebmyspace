/**
 * Copyright. INRIA
 *
 * Web page extractor
 *
 * This software is governed by the CeCILL-C license under French law and
 * abiding by the rules of distribution of free software.  You can  use, 
 * modify and/ or redistribute the software under the terms of the CeCILL-C
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * <a href="http://www.cecill.info">http://www.cecill.info</a> . 
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability. 
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or 
 * data to be ensured and,  more generally, to use and operate it in the 
 * same conditions as regards security. 
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 * 
 */
package fr.inria.edelweiss.extractor.webpage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class providing methods to extract and access metadata (HTTP header and HTML meta and link tags)
 * and text content of web pages.
 *  
 * @author Fabien Gandon
 * @version 1.1
 * 
 */
public class WebPageExtractor {

    protected static final String ELEMENT_RDF = "rdf:RDF";
    protected static final String NS_RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    protected static final String NS_WEBEXTRACTOR = "http://ns.inria.fr/edelweiss/extractor/webpage#";
    protected static final String RDF_RESOURCE = "rdf:resource";
    protected static final String RDF_ABOUT = "rdf:about";
    private Map m_http_header_fields = null;
    private URL m_URL = null;
    private String m_host = null;
    private String m_top_domain = null;
    private String m_content_type = null;
    private String m_content_encoding = null;
    private String m_default_content_encoding = "utf-8";
    private Date m_connection_date = null;
    private Date m_last_modified = null;
    private String m_IP_address = null;
    private String m_title = null;
    /** list of meta elements in this HTML page */
    private LinkedList<Meta> m_meta = null;
    /** list of content elements (text, headers, images, objects, links) in this HTML page */
    private LinkedList<ContentBlock> m_content = null;
    /** list of link elements in this HTML page */
    private LinkedList<Link> m_links = null;
    /** list of table elements in this HTML page */
    private LinkedList<Table> m_tables = null;
    /** list of div elements in this HTML page */
    private LinkedList<Div> m_divs = null;

    private LinkedList<Anchor> m_anchor = null;

    public WebPageExtractor() {
        super();
    }

    public WebPageExtractor(String p_URL) throws MalformedURLException {
        super();
        this.setURL(p_URL);
    }

    /**
     * @param p_URL URL of the web page to be parsed.
     */
    public WebPageExtractor(URL p_URL) {
        super();
        this.setURL(p_URL);
    }

    /**
     * @return connection date of the extraction
     * @see #getLastModified()
     */
    public final Date getConnectionDate() {
        return m_connection_date;
    }

    /**
     * @return content type from HTTP header e.g. "text/html; charset=UTF-8"
     */
    public final String getContentType() {
        return m_content_type;
    }

    /**
     * @return IP address of the server serving the page
     */
    public final String getIPAddress() {
        return m_IP_address;
    }

    /**
     * @return date of the last modification of this page
     * @see #getConnectionDate()
     */
    public final Date getLastModified() {
        return m_last_modified;
    }

    /**
     * @return value of the TITLE html element.
     */
    public final String getTitle() {
        return m_title;
    }

    /**
     * @param p_URL string representation of the URL of the page to be parsed
     * @throws MalformedURLException
     */
    public void setURL(String p_URL) throws MalformedURLException {
        if (p_URL != null) {
            m_URL = getOrCreateURL(p_URL);
        } else {
            throw new MalformedURLException("The URL of web page to be extracted cannot be null.");
        }
    }

    private static URL getOrCreateURL(String name) throws MalformedURLException {
        try {
            return new URL(name);
        } catch (MalformedURLException mue) {
            return (new File(name)).toURI().toURL();
        }
    }

    /**
     * @param p_URL URL of the page to be parsed
     */
    public void setURL(URL p_URL) {
        m_URL = p_URL;
    }

    /**
     * @return the encoding uses by default if not found at connection time
     */
    public final String getDefaultContentEncoding() {
        return m_default_content_encoding;
    }

    /**
     * @param p_default_content_encoding the encoding uses by default if not found at connection time
     */
    public final void setDefaultContentEncoding(String p_default_content_encoding) {
        this.m_default_content_encoding = p_default_content_encoding;
    }

    /**
     * @return the LinkedList of the content blocks of the document
     * @see Anchor
     * @see Embedded
     * @see Header
     * @see Image
     * @see Link
     * @see Paragraph
     * @see ContentBlock
     */
    public final LinkedList<ContentBlock> getContent() {
        return m_content;
    }

    /**
     * @return the LinkedList of the &lt;link&gt; elements of the document
     * @see Link
     */
    public final LinkedList<Link> getLinks() {
        return m_links;
    }

    /**
     * @return the LinkedList of the &lt;meta&gt; elements of the document
     * @see Meta
     */
    public final LinkedList<Meta> getMeta() {
        return m_meta;
    }

    /**
     * connects to the URL and parses the page to update all the extracted data.
     * @throws IOException
     * @throws MalformedURLException
     */
    /**
     * @throws IOException
     * @throws MalformedURLException
     */
    public void extract() throws IOException, MalformedURLException {
        if (m_URL != null) {
            URLConnection l_connection = m_URL.openConnection();

            m_host = m_URL.getHost();
            m_top_domain = m_host.substring(1 + m_host.lastIndexOf("."));
            m_http_header_fields = l_connection.getHeaderFields();

            m_content_type = l_connection.getContentType();
            m_content_encoding = l_connection.getContentEncoding();

            m_connection_date = new Date(l_connection.getDate());
            m_last_modified = new Date(l_connection.getLastModified());

            if (m_content_encoding == null) {
                m_content_encoding = StringUtils.substringBetween(l_connection.getContentType() + " ", "charset=", " ");
                if (m_content_encoding == null) {
                    m_content_encoding = m_default_content_encoding;
                }
            }

            InetAddress l_inet_address = InetAddress.getByName(m_host);
            if (l_inet_address != null) {
                m_IP_address = l_inet_address.getHostAddress();
            } else {
                m_IP_address = null;
            }

            parse();
        } else {
            throw new MalformedURLException(
                    "The URL of web page to be extracted was not set.");
        }
    }

    /**
     * @return the host name of the server serving the the page
     */
    public final String getHost() {
        return m_host;
    }

    /**
     * @return map of all the HTTP headers fields see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html">Header Field Definitions</a>
     */
    public final Map getHttpHeaderFields() {
        return m_http_header_fields;
    }

    /**
     * @return top domain of host name e.g. "www.inria.fr" gives "fr"
     */
    public final String getTopDomain() {
        return m_top_domain;
    }

    /**
     * @return URL of the page being parsed
     */
    public final URL getURL() {
        return m_URL;
    }

    /**
     * HTML code light parser
     * @throws IOException
     */
    private void parse() throws IOException {
        // reset fields
        m_meta = new LinkedList<Meta>();
        m_content = new LinkedList<ContentBlock>();
        m_links = new LinkedList<Link>();
        m_tables = new LinkedList<Table>();
        m_divs = new LinkedList<Div>();
        m_anchor = new LinkedList<Anchor>();

        // current block of HTML being extracted
        StringBuffer block = new StringBuffer();

        // typed container for the block once extracted e.g. Paragraph, Header, etc.
        ContentBlock current = new Paragraph();

        // Tokenizer for HTML tags and text
        URLLexicalAnalyzer lexical = new URLLexicalAnalyzer(m_URL,
                m_content_encoding);

        String l_part = lexical.getNextPart();

        while (l_part != null) {
            String l_low_part = l_part.toLowerCase();
            if (isTable(l_low_part)) // extract <table> of document
            {
                m_tables.add(new Table(extractAttributeValue("id", l_part)));
            } else if (isDiv(l_low_part)) // extract <div> of document
            {
                m_divs.add(new Div(extractAttributeValue("class", l_part), extractAttributeValue("friendid", l_part)));
            } else if (isTag(l_low_part)) {
                if (isTitle(l_low_part)) {
                    m_title = lexical.getNextPart(); 	// extract <title> of document
                } else if (isScript(l_low_part)) // ignore scripts
                {
                    String next = null;
                    do {
                        next = lexical.getNextPart().toLowerCase();
                    } while (next != null && !endScript(next));
                } else if (isStyle(l_low_part)) // ignore styles
                {
                    String next = null;
                    do {
                        next = lexical.getNextPart();
                    } while (next != null && !endStyle(next));
                } else if (isAnchor(l_low_part)) // extract anchors i.e. <a href="">
                {
                    String next = null;
                    StringBuffer l_anchor_text = new StringBuffer();
                    // get text of the anchor
                    do {
                        next = lexical.getNextPart();
                        if (!isTag(next)) {
                            l_anchor_text.append(next);
                        }
                    } while (next != null && !isBlock(next) && !endAnchor(next));

                    // flush current block before adding anchor
                    String p_content = StringEscapeUtils.unescapeHtml(
                            block.toString()).trim();
                    if (p_content.length() > 1) {
                        current.setText(p_content);
                        m_content.add(current);
                    }

                    if (extractAttributeValue("class", l_part) != null && extractAttributeValue("class", l_part).equals("msProfileTextLink")) {
                        m_anchor.add(new Anchor(extractAttributeValue("name",
                                l_part), extractAttributeValue("href", l_part),
                                extractAttributeValue("rel", l_part),
                                extractAttributeValue("rev", l_part),
                                StringEscapeUtils.unescapeHtml(l_anchor_text.toString()),
                                extractAttributeValue("title", l_part)));
                    }
                    m_content.add(new Anchor(extractAttributeValue("name",
                            l_part), extractAttributeValue("href", l_part),
                            extractAttributeValue("rel", l_part),
                            extractAttributeValue("rev", l_part),
                            StringEscapeUtils.unescapeHtml(l_anchor_text.toString()),
                            extractAttributeValue("title", l_part)));
                    // prepare for next block using type of previous block
                    block = new StringBuffer();
                    try {
                        current = current.getClass().newInstance();
                    } catch (InstantiationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (isMeta(l_low_part)) // extract <meta> of document
                {
                    m_meta.add(new Meta(extractAttributeValue("name", l_part),
                            extractAttributeValue("lang", l_part),
                            extractAttributeValue("content", l_part),
                            extractAttributeValue("scheme", l_part),
                            extractAttributeValue("http-equiv", l_part)));

                } else if (isLink(l_low_part)) // extract <link> of document
                {
                    m_links.add(new Link(extractAttributeValue("charset",
                            l_part), extractAttributeValue("href", l_part),
                            extractAttributeValue("hreflang", l_part),
                            extractAttributeValue("type", l_part),
                            extractAttributeValue("rel", l_part),
                            extractAttributeValue("rev", l_part),
                            extractAttributeValue("media", l_part)));
                } else if (isImage(l_low_part)) // extract <img> of document
                {
                    //	flush current block before adding image
                    String p_content = StringEscapeUtils.unescapeHtml(
                            block.toString()).trim();
                    if (p_content.length() > 1) {
                        current.setText(p_content);
                        m_content.add(current);
                    }
                    m_content.add(new Image(
                            extractAttributeValue("src", l_part),
                            extractAttributeValue("alt", l_part),
                            extractAttributeValue("title", l_part)));

                    //	prepare for next block using type of previous block
                    block = new StringBuffer();
                    try {
                        current = current.getClass().newInstance();
                    } catch (InstantiationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (isEmbedded(l_low_part)) // extract <embedded> and <object> of document
                {
                    //	flush current block before adding embedded
                    if (block.toString().trim().length() > 1) {
                        current.setText(block.toString());
                        m_content.add(current);
                    }
                    m_content.add(new Embedded(extractAttributeValue("src",
                            l_part), extractAttributeValue("alt", l_part),
                            extractAttributeValue("name", l_part),
                            extractAttributeValue("data", l_part),
                            extractAttributeValue("codebase", l_part)));

                    // prepare for next block using type of previous block
                    block = new StringBuffer();
                    try {
                        current = current.getClass().newInstance();
                    } catch (InstantiationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else if (isHeader(l_low_part)) {		// new header
                    String p_content = StringEscapeUtils.unescapeHtml(
                            block.toString()).trim();
                    if (p_content.length() > 1) {
                        current.setText(p_content);
                        m_content.add(current);
                    }
                    block = new StringBuffer();
                    current = new Header();
                    ((Header) current).setLevel(Short.parseShort("" + l_low_part.charAt(2)));

                } else if (endHeader(l_low_part) || endBlock(l_low_part) || isBlock(l_low_part)) { 		// new paragraph
                    String p_content = StringEscapeUtils.unescapeHtml(
                            block.toString()).trim();
                    if (p_content.length() > 1) {
                        current.setText(p_content);
                        m_content.add(current);
                    }
                    block = new StringBuffer();
                    current = new Paragraph();
                }

            } else if (l_part.length() > 1) {
                block.append(" " + l_part);
            }

            l_part = lexical.getNextPart();
        }
        // flush any remaining text
        String p_content = StringEscapeUtils.unescapeHtml(block.toString()).trim();
        if (p_content.length() > 1) {
            current.setText(p_content);
            m_content.add(current);
        }

        lexical.close();
    }

    /**
     * extract the value of an attribute from an element
     * @param p_attributeName
     * @param p_Element
     * @return value of the attribute
     */
    private static String extractAttributeValue(String p_attributeName,
            String p_Element) {
        String value = null;
        String l_low = p_Element.toLowerCase();
        if (l_low.indexOf(p_attributeName + "=\"") > -1) {
            value = p_Element.substring(p_attributeName.length() + 2 + l_low.indexOf(p_attributeName + "=\""));
            if (value.indexOf("\"") > -1) {
                value = value.substring(0, value.indexOf("\""));
            } else if (value.indexOf(">") > -1) {
                value = value.substring(0, value.indexOf(">"));
            }
        } else if (l_low.indexOf(p_attributeName + "='") > -1) {
            value = p_Element.substring(p_attributeName.length() + 2 + l_low.indexOf(p_attributeName + "='"));
            if (value.indexOf("'") > -1) {
                value = value.substring(0, value.indexOf("'"));
            } else if (value.indexOf(">") > -1) {
                value = value.substring(0, value.indexOf(">"));
            }
        }
        return StringEscapeUtils.unescapeHtml(value);
    }

    private static boolean isTag(String word) {
        return (word.startsWith("<"));
    }

    private static boolean isStyle(String word) {
        return (word.startsWith("<style"));
    }

    private static boolean endStyle(String word) {
        return (word.startsWith("</style"));
    }

    private static boolean isTitle(String word) {
        return (word.startsWith("<title"));
    }

    private static boolean isImage(String word) {
        return (word.startsWith("<img"));
    }

    private static boolean isAnchor(String word) {
        return (word.startsWith("<a"));
    }

    private static boolean endAnchor(String word) {
        return (word.startsWith("</a"));
    }

    private static boolean isEmbedded(String word) {
        return (word.startsWith("<embed") || word.startsWith("<object"));
    }

    private static boolean isHeader(String word) {
        return (word.startsWith("<h1") || word.startsWith("<h2") || word.startsWith("<h3") || word.startsWith("<h4") || word.startsWith("<h5") || word.startsWith("<h6"));
    }

    private static boolean endHeader(String word) {
        return (word.startsWith("</h"));
    }

    private static boolean isBlock(String word) {
        return (word.startsWith("<p") || word.startsWith("<div"));
    }

    private static boolean endBlock(String word) {
        return (word.startsWith("</p") || word.startsWith("</div"));
    }

    private static boolean isMeta(String word) {
        return (word.startsWith("<meta"));
    }

    private static boolean isLink(String word) {
        return (word.startsWith("<link"));
    }

    private static boolean isScript(String word) {
        return (word.startsWith("<script"));
    }

    private static boolean endScript(String word) {
        return (word.startsWith("</script"));
    }

    private static boolean isTable(String word) {
        return (word.startsWith("<table"));
    }

    private static boolean endTable(String word) {
        return (word.startsWith("</table"));
    }

    private static boolean isDiv(String word) {
        return (word.startsWith("<div"));
    }

    private static boolean endDiv(String word) {
        return (word.startsWith("</div"));
    }

    /**
     * @return the m_tables
     */
    public LinkedList<Table> getTables() {
        return m_tables;
    }

    /**
     * @param m_tables the m_tables to set
     */
    public void setTables(LinkedList<Table> m_tables) {
        this.m_tables = m_tables;
    }

    /**
     * @return the m_divs
     */
    public LinkedList<Div> getDivs() {
        return m_divs;
    }

    public LinkedList<Anchor> getAnchors() {
        return m_anchor;
    }

    /**
     * @param m_divs the m_divs to set
     */
    public void setDivs(LinkedList<Div> m_divs) {
        this.m_divs = m_divs;
    }

    /**
     * retrieves the source code of a web page respecting the encoding
     * @author Fabien Gandon
     * @version 1.0
     *
     */
    class URLLexicalAnalyzer {

        private BufferedReader m_reader;
        private StringBuffer buffer = new StringBuffer();

        public URLLexicalAnalyzer(URL p_url, String p_encoding) {
            try {

                URLConnection con = p_url.openConnection();
                InputStream uc = con.getInputStream();
                m_reader = new BufferedReader(new InputStreamReader(uc, p_encoding));
            } catch (IOException io) {
                System.out.println("ERROR, file not found " + p_url);
                System.exit(1);
            }
        }

        public void close() {
            try {
                if (null != m_reader) {
                    m_reader.close();
                }
            } catch (IOException ignored) {
            }
        }

        /**
         * @return next piece of text or next element or null if there is no more.
         * @throws IOException
         */
        public String getNextPart() throws IOException {

            int c = -1;
            c = m_reader.read();

            while (c != '<' && c != '>' && c != -1) {
                if (Character.isWhitespace((char) c)) {
                    while (c != -1 && Character.isWhitespace((char) c)) // absorb multiple spaces
                    {
                        c = m_reader.read();
                    }
                    buffer.append(" ");
                } else {
                    buffer.append((char) c);
                    c = m_reader.read();
                }
            }

            String result = null;
            if (c == '<') {
                result = buffer.toString();
                buffer = new StringBuffer("<");
            } else if (c == '>') {
                buffer.append(">");
                result = buffer.toString();
                buffer = new StringBuffer();
            } else if (c == -1) {
                return null;
            } else {
                result = buffer.toString();
                buffer = new StringBuffer();
            }
            return result.trim();
        }
    }

    public String toString() {
        return "WebPageExtractor for " + m_URL + "\n" + "HTTP header" + m_http_header_fields + "\n" + "Host : " + m_host + "\n" + "Top domain : " + m_top_domain + "\n" + "Content type : " + m_content_type + "\n" + "Content encoding : " + m_content_encoding + "\n" + "Default content encoding : " + m_default_content_encoding + "\n" + "Connection date : " + m_connection_date + "\n" + "Last modified : " + m_last_modified + "\n" + "IP Address : " + m_IP_address + "\n" + "Title : " + m_title + "\n" + "Meta : " + m_meta + "\n" + "Links : " + m_links + "\n" + "Content : \n" + m_content;
    }

    /**
     * @return the full raw text of the document
     */
    public String fullText() {
        Iterator<ContentBlock> iter = m_content.iterator();
        StringBuffer content = new StringBuffer();
        while (iter.hasNext()) {
            content.append(" " + iter.next().getText());
        }
        return content.toString().trim();
    }

    /**
     * @return the DOM of the XML/RDF representation of the result of the extraction.
     */
    public Document getRDFXML() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document result = builder.newDocument();
        Element RDFroot = result.createElementNS(NS_RDF, ELEMENT_RDF);
        result.appendChild(RDFroot);

        Element Extract_root = result.createElementNS(NS_WEBEXTRACTOR, "Extract");
        RDFroot.appendChild(Extract_root);

        Attr resource = result.createAttribute(RDF_ABOUT);
        resource.setValue(m_URL.toString());
        Extract_root.setAttributeNode(resource);

        Element tmp = result.createElement("title");
        tmp.setTextContent(StringEscapeUtils.escapeXml(m_title));
        Extract_root.appendChild(tmp);

        tmp = result.createElement("ip");
        tmp.setTextContent(m_IP_address);
        Extract_root.appendChild(tmp);

        tmp = result.createElement("last_modified");
        tmp.setTextContent(m_last_modified.toString());
        Extract_root.appendChild(tmp);

        tmp = result.createElement("connection_date");
        tmp.setTextContent(m_connection_date.toString());
        Extract_root.appendChild(tmp);

        tmp = result.createElement("host");
        tmp.setTextContent(m_host);
        Extract_root.appendChild(tmp);

        tmp = result.createElement("top_domain");
        tmp.setTextContent(m_top_domain);
        Extract_root.appendChild(tmp);

        tmp = result.createElement("content_encoding");
        tmp.setTextContent(m_content_encoding);
        Extract_root.appendChild(tmp);

        tmp = result.createElement("content_type");
        tmp.setTextContent(m_content_type);
        Extract_root.appendChild(tmp);

        for (String s : (Iterable<String>) m_http_header_fields.keySet()) {
            if (s != null) {
                tmp = result.createElement(s.replace('-', '_').toLowerCase());
                tmp.setTextContent(StringEscapeUtils.escapeXml(m_http_header_fields.get(s).toString().replace("[", "").replace("]", "")));
                Extract_root.appendChild(tmp);
            }
        }

        tmp = result.createElement("has_link");
        Extract_root.appendChild(tmp);
        Element tmp2 = result.createElement("rdf:Bag");
        tmp.appendChild(tmp2);
        Element tmp3 = null;

        for (Link link : (Iterable<Link>) m_links) {
            tmp3 = result.createElement("rdf:li");
            tmp3.appendChild(link.toRDFXML(result));
            tmp2.appendChild(tmp3);
        }

        tmp = result.createElement("has_meta");
        Extract_root.appendChild(tmp);
        tmp2 = result.createElement("rdf:Bag");
        tmp.appendChild(tmp2);

        for (Meta meta : (Iterable<Meta>) m_meta) {
            tmp3 = result.createElement("rdf:li");
            tmp3.appendChild(meta.toRDFXML(result));
            tmp2.appendChild(tmp3);
        }

        tmp = result.createElement("has_content");
        Extract_root.appendChild(tmp);
        tmp2 = result.createElement("rdf:Seq");
        tmp.appendChild(tmp2);

        for (ContentBlock block : (Iterable<ContentBlock>) m_content) {

            tmp3 = result.createElement("rdf:li");
            tmp3.appendChild(block.toRDFXML(result));
            tmp2.appendChild(tmp3);
        }

        return result;
    }

    /**
     * from http://www.raben.com/articles/XsltEditor/part_1.html
     * @return the RDF/XML representation converted to a string
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public String getSerializedRDFXML() throws TransformerException, ParserConfigurationException {

        // Create dom source for the document
        DOMSource domSource = new DOMSource(this.getRDFXML());

        // Create a string writer
        StringWriter stringWriter = new StringWriter();

        // Create the result stream for the transform
        StreamResult result = new StreamResult(stringWriter);

        // Create a Transformer to serialize the document
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");

        // Transform the document to the result stream
        transformer.transform(domSource, result);
        return stringWriter.toString();
    }

    /**
     * testing purposes ; params are not used.
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (String s : args) {
                WebPageExtractor extractor = new WebPageExtractor(getOrCreateURL(s));
                extractor.extract();
                System.out.println(extractor.toString());
                System.out.println("========================\n" + extractor.fullText());
                System.out.println(extractor.getSerializedRDFXML());
            }
            if (args.length == 0) {
                WebPageExtractor extractor = new WebPageExtractor(new URL("http://www-sop.inria.fr/edelweiss/people/Fabien.Gandon/wakka.php?wiki=FabienGandon"));
                extractor.extract();
                System.out.println(extractor.toString());
                System.out.println("========================\n" + extractor.fullText());
                System.out.println(extractor.getSerializedRDFXML());
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
