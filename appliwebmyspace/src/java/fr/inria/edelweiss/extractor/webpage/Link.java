package fr.inria.edelweiss.extractor.webpage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML element &lt;link&gt; and provides the getters for its attributes.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Link {
	private String charset = null;

	private String href = null;

	private String hreflang = null;

	private String type = null;

	private String rel = null;

	private String rev = null;

	private String media = null;

	public Link(String charset, String href, String hreflang, String type,
			String rel, String rev, String media) {
		super();
		this.charset = charset;
		this.href = href;
		this.hreflang = hreflang;
		this.type = type;
		this.rel = rel;
		this.rev = rev;
		this.media = media;
	}

	public final String getCharset() {
		return charset;
	}

	public final String getHref() {
		return href;
	}

	public final String getHreflang() {
		return hreflang;
	}

	public final String getMedia() {
		return media;
	}

	public final String getRel() {
		return rel;
	}

	public final String getRev() {
		return rev;
	}

	public final String getType() {
		return type;
	}

	public String toString() {
		return "(charset: " + this.charset + " href: " + this.href
				+ " hreflang: " + this.hreflang + " type: " + this.type
				+ " rel: " + this.rel + " rev: " + this.rev + " media: "
				+ this.media + ")";
	}

	public Element toRDFXML(Document doc)
	{
		Element link =doc.createElement("Link");
		Element tmp= null;
		
		if(this.getHref()!=null)
		{
			tmp=doc.createElement("href");
			tmp.setAttribute(WebPageExtractor.RDF_RESOURCE, this.getHref());
			link.appendChild(tmp);
		}
		
		if(this.getHreflang()!=null)
		{
			tmp=doc.createElement("href_lang");
			tmp.setTextContent(this.getHreflang());
			link.appendChild(tmp);
		}
		
		if(this.getMedia()!=null)
		{
			tmp=doc.createElement("media");
			tmp.setTextContent(this.getMedia());
			link.appendChild(tmp);
		}
	
		if(this.getRel()!=null)
		{
			tmp=doc.createElement("rel");
			tmp.setTextContent(this.getRel());
			link.appendChild(tmp);
		}

		if(this.getRev()!=null)
		{
			tmp=doc.createElement("rev");
			tmp.setTextContent(this.getRev());
			link.appendChild(tmp);
		}
		
		if(this.getType()!=null)
		{
			tmp=doc.createElement("type");
			tmp.setTextContent(this.getType());
			link.appendChild(tmp);
		}
		return link;
	}
}
