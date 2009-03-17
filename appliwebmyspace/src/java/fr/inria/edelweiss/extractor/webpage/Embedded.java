package fr.inria.edelweiss.extractor.webpage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML elements &lt;object&gt; and &lt;embed&gt; and provides the getters for their attributes.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Embedded extends ContentBlock {

	private String src = null;

	private String codebase = null;

	private String data = null;

	private String name = null;

	public final String getSrc() {
		return src;
	}

	public final String getCodebase() {
		return codebase;
	}

	public final String getData() {
		return data;
	}

	public final String getName() {
		return name;
	}

	public Embedded() {
		super();
	}

	public Embedded(String p_src, String p_alt, String p_name, String p_data,
			String p_codebase) {
		super(p_alt);
		src = p_src;
		name = p_name;
		data = p_data;
		codebase = p_codebase;
	}

	public String toString() {
		return "(Embedded " + src + " '" + name + "' '" + getText() + "' '"
				+ data + " '" + codebase + "')\n";
	}

	public Element toRDFXML(Document doc)
	{
		Element embedded =doc.createElement("Embedded");
		Element tmp= null;

		if(this.getText()!=null)
		{
			tmp=doc.createElement("text");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.getText()));
			embedded.appendChild(tmp);
		}

		if(this.name!=null)
		{
			tmp=doc.createElement("name");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.name));
			embedded.appendChild(tmp);
		}
		
		if(this.src!=null)
		{
			tmp=doc.createElement("src");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.src));
			embedded.appendChild(tmp);
		}

		if(this.data!=null)
		{
			tmp=doc.createElement("data");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.data));
			embedded.appendChild(tmp);
		}
		

		if(this.codebase!=null)
		{
			tmp=doc.createElement("codebase");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.codebase));
			embedded.appendChild(tmp);
		}
		
		return embedded;
	}
}
