/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.edelweiss.extractor.webpage;

/**
 *
 * @author guillaume
 */
public class Table extends ContentBlock {

    private String tableClass = null;

    public final String getTableClass() {
        return tableClass;
    }

    public Table() {
        super();
    }

    public Table(String tableClass) {
        super();
        this.tableClass = tableClass;
    }

    public String toString() {
        return "(Table " + tableClass + " '" + getText() + "')\n";
    }
}
