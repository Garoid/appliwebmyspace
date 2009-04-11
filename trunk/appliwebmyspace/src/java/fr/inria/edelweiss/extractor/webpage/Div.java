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
    private String friendId = null;

    public final String getDivClass() {
        return divClass;
    }

    public final String getFriendId() {
        return friendId;
    }

    public Div() {
        super();
    }

    public Div(String divClass, String friendId) {
        super();
        this.divClass = divClass;
        this.friendId = friendId;
    }

    public String toString() {
        return getText()+"\n";
    }
}
