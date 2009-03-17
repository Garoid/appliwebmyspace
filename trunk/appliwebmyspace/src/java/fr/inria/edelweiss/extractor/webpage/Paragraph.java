package fr.inria.edelweiss.extractor.webpage;

/**
 * reprensents the HTML element &lt;p&gt; and &lt;div&gt; and any piece of text.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Paragraph extends ContentBlock {

	public Paragraph() {
		super();
	}

	public Paragraph(String p_src, String text) {
		super(text);
	}

	public String toString() {
		return "(Paragraph) " + super.toString() + "\n";
	}
}
