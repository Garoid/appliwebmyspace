package fr.inria.edelweiss.extractor.webpage;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML elements &lt;h1&gt; ... &lt;h6&gt; and provides the getters for their attributes.
 * @author Fabien Gandon
 * @version 1.1
 * 
 */
public class Header extends ContentBlock {

	private short level=1;
	
	public Header() {
		super();
	}

	public Header(String text) {
		super(text);
	}

	public String toString() {
		return "(Header "+level+") " + super.toString() + "\n";
	}

	/**
	 * @return level of the header e.g. 1 for H1, 2 for H2, etc.
	 */
	public final short getLevel() {
		return level;
	}

	public final void setLevel(short level) {
		this.level = level;
	}

	public Element toRDFXML(Document doc)
	{
		Element header =doc.createElement("Header");
		Element tmp= null;

		if(this.getText()!=null)
		{
			tmp=doc.createElement("text");
			tmp.setTextContent(StringEscapeUtils.escapeXml(this.getText()));
			header.appendChild(tmp);
		}

		tmp=doc.createElement("level");
		tmp.setTextContent(StringEscapeUtils.escapeXml(""+this.level));
		header.appendChild(tmp);	
		
		return header;
	}
}
