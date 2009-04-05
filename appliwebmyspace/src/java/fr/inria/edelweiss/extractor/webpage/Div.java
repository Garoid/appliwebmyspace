/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.edelweiss.extractor.webpage;

/**
 *
 * @author guillaume
 */
public class Div extends ContentBlock {

    private String divClass = null;

    public final String getDivClass() {
        return divClass;
    }

    public Div() {
        super();
    }

    public Div(String divClass) {
        super();
        this.divClass = divClass;
    }

    public String toString() {
        return getText()+"\n";
    }
}
