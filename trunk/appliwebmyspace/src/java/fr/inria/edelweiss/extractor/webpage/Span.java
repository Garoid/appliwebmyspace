/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.edelweiss.extractor.webpage;

/**
 *
 * @author guillaume
 */
public class Span extends ContentBlock {

    private String spanClass = null;
    private String spanId = null;
    private String spanContent = null;

    public final String getSpanClass() {
        return spanClass;
    }

    public final String getSpanId() {
        return spanId;
    }

    public final String getSpanContent() {
        return spanContent;
    }

    public Span() {
        super();
    }

    public Span(String spanClass) {
        super();
        this.spanClass = spanClass;
    }

    public Span(String spanClass, String id) {
        super();
        this.spanClass = spanClass;
        this.spanId = id;
    }

    public Span(String spanClass, String id, String text) {
        super();
        this.spanClass = spanClass;
        this.spanId = id;
        this.spanContent = text;
    }

    public String toString() {
        return getText() + "\n";
    }
}
