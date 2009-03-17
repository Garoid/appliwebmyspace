package fr.inria.edelweiss.extractor.webpage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML element &lt;img&gt; and provides the getters for its attributes.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Image extends ContentBlock {

	private String src = null;

	private String title = null;

	public final String getSrc() {
		return src;
	}

	public final String getTitle() {
		return title;
	}

	public Image() {
		super();
	}

	public Image(String p_src, String p_alt, String p_title) {
		super(p_alt);
		src = p_src;
		title = p_title;
	}

	public String toString() {
		return "(Image " + src + " '" + title + "' '" + getText() + "')\n";
	}

	public Element toRDFXML(Document doc)
	{
		Element image =doc.createElement("Image");
		Element tmp= null;

		if(this.getText()!=null)
		{
			tmp=doc.createElement("text");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.getText()));
			image.appendChild(tmp);
		}

		if(this.title!=null)
		{
			tmp=doc.createElement("title");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.title));
			image.appendChild(tmp);
		}
		
		if(this.src!=null)
		{
			tmp=doc.createElement("src");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.src));
			image.appendChild(tmp);
		}
		
		return image;
	}
}
