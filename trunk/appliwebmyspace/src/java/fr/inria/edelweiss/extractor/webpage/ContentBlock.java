package fr.inria.edelweiss.extractor.webpage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * parent of all the HTML elements with a geter for the default text value / content.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
abstract public class ContentBlock {

	private String text = null;

	public ContentBlock() {
		super();
	}

	public ContentBlock(String text) {
		super();
		this.text = text;
	}

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return this.getText();
	}

	public Element toRDFXML(Document doc)
	{
		Element content =doc.createElement("Content");
		Element tmp= null;

		if(this.getText()!=null)
		{
			tmp=doc.createElement("text");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.getText()));
			content.appendChild(tmp);
		}
		
		return content;
	}
}
