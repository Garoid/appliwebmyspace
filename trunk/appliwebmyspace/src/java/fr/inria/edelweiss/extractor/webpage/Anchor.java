package fr.inria.edelweiss.extractor.webpage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML element &lt;a&gt; and provides the getters for its attributes.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Anchor extends ContentBlock {

	private String name = null;

	private String href = null;

	private String rel = null;

	private String rev = null;

	public Anchor() {
		super();
	}

	public Anchor(String p_name, String p_href, String p_rel, String p_rev,
			String p_text) {
		super(p_text);
		this.name = p_name;
		this.href = p_href;
		this.rel = p_rel;
		this.rev = p_rev;
	}

	public final String getHref() {
		return href;
	}

	public final String getName() {
		return name;
	}

	public final String getRel() {
		return rel;
	}

	public final String getRev() {
		return rev;
	}

	public String toString() {
		return "(Anchor " + name + " '" + href + "' '" + rel + "' '" + rev
				+ "' '" + getText() + "')\n";
	}


	public Element toRDFXML(Document doc)
	{
		Element anchor =doc.createElement("Anchor");
		Element tmp= null;

		if(this.getText()!=null)
		{
			tmp=doc.createElement("text");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.getText()));
			anchor.appendChild(tmp);
		}

		if(this.name!=null)
		{
			tmp=doc.createElement("name");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.name));
			anchor.appendChild(tmp);
		}
		
		if(this.href!=null)
		{
			tmp=doc.createElement("href");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.href));
			anchor.appendChild(tmp);
		}

		if(this.rel!=null)
		{
			tmp=doc.createElement("rel");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.rel));
			anchor.appendChild(tmp);
		}
		

		if(this.rev!=null)
		{
			tmp=doc.createElement("rev");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.rev));
			anchor.appendChild(tmp);
		}
		
		return anchor;
	}
}
