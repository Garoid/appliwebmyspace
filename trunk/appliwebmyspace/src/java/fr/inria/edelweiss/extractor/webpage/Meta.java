package fr.inria.edelweiss.extractor.webpage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * reprensents the HTML element &lt;meta&gt; and provides the getters for its attributes.
 * @author Fabien Gandon
 * @version 1.0
 * 
 */
public class Meta {
	private String name = null;

	private String lang = null;

	private String content = null;

	private String scheme = null;

	private String http_equiv = null;

	public Meta(String name, String lang, String content, String scheme,
			String http_equiv) {
		super();
		this.name = name;
		this.lang = lang;
		this.content = content;
		this.scheme = scheme;
		this.http_equiv = http_equiv;
	}

	public final String getContent() {
		return content;
	}

	public final String getHttp_equiv() {
		return http_equiv;
	}

	public final String getLang() {
		return lang;
	}

	public final String getName() {
		return name;
	}

	public final String getScheme() {
		return scheme;
	}

	public String toString() {
		return "(name: " + this.name + " lang: " + this.lang + " content: "
				+ this.content + " scheme: " + this.scheme + " http-equiv: "
				+ this.http_equiv + ")";
	}

	public Element toRDFXML(Document doc)
	{
		Element meta =doc.createElement("Meta");
		Element tmp= null;

		if(this.getContent()!=null)
		{
			tmp=doc.createElement("content");
			tmp.setTextContent(this.getContent());
			meta.appendChild(tmp);
		}

		if(this.getLang()!=null)
		{
			tmp=doc.createElement("lang");
			tmp.setTextContent(this.getLang());
			meta.appendChild(tmp);
		}
		
		if(this.getHttp_equiv()!=null)
		{
			tmp=doc.createElement("http_equiv");
			tmp.setTextContent(this.getHttp_equiv());
			meta.appendChild(tmp);
		}

		if(this.getName()!=null)
		{
			tmp=doc.createElement("name");
			tmp.setTextContent(this.getName());
			meta.appendChild(tmp);
		}

		if(this.getScheme()!=null)
		{
			tmp=doc.createElement("scheme");
			tmp.setTextContent(this.getScheme());
			meta.appendChild(tmp);
		}		
		
		return meta;
	}
}
