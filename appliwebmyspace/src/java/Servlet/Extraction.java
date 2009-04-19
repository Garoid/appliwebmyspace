/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import fr.inria.edelweiss.extractor.webpage.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONArray;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.json.simple.JSONObject;

/**
 *
 * @author guillaume
 */
public class Extraction extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static int niveau = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParserConfigurationException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("id") != null) {
                WebPageExtractor extractor = new WebPageExtractor(new URL("http://friends.myspace.com/index.cfm?fuseaction=user.viewfriends&friendID=" + request.getParameter("id")));
                extractor.extract();

                if (!extractor.getTitle().equalsIgnoreCase("Invalid Friend ID")) {
                    String nomProfil = "";
                    String[] tab = extractor.getTitle().split("Amis de ");
                    nomProfil = tab[1];
                    niveau = Integer.parseInt(request.getParameter("niveau"));

                    JSONObject profil = createFriend(request.getParameter("id"), nomProfil, extractNombreAmis(request.getParameter("id")), 8, -33);

                    int profAmi = Integer.parseInt(request.getParameter("prof"));
                    
                    JSONArray profilChildren = getFriends(request.getParameter("id"),profAmi,1);

                    profil.put("children", profilChildren);

                    out.print(profil);
                    out.flush();
                } else {
                    out.println("L'ID " + request.getParameter("id") + " ne correspond à aucun profil dans MySpace...");
                }
            }
        } finally {
            out.close();
        }
    }

    protected JSONArray getFriends(String id, int profAmi, int n) throws MalformedURLException, IOException {
        if (n <= niveau) {
            WebPageExtractor extractor = new WebPageExtractor(new URL("http://friends.myspace.com/index.cfm?fuseaction=user.viewfriends&friendID=" + id));
            extractor.extract();
            int indexF = 0;
            JSONArray amis = new JSONArray();
            for (Div div : extractor.getDivs()) {
                if (div.getDivClass() != null && div.getDivClass().equals("friendHelperBox") && indexF < profAmi) {
                    JSONObject ami = createFriend(div.getFriendId(), extractor.getAnchors().get(indexF).getTitle(), extractNombreAmis(div.getFriendId()), 0, 0);
                    JSONArray amiChildren = getFriends(div.getFriendId(), profAmi, n + 1);

                    ami.put("children", amiChildren);
                    amis.add(ami);

                    indexF++;

                }
                if (indexF == profAmi) {
                    break;
                }

            }
            return amis;
        }
        return new JSONArray();
    }

    protected JSONObject createFriend(String id, String name, String nbAmis, Integer keyI1, Integer keyI2) {
        JSONObject obj = new JSONObject();
        JSONObject key = new JSONObject();
        JSONObject key2 = new JSONObject();
        JSONArray array = new JSONArray();

        key.put("key", "key1");
        key.put("value", keyI1);
        key2.put("key", "key2");
        key2.put("value", keyI2);

        array.add(key);
        array.add(key2);

        obj.put("id", id);
        obj.put("name", name);
        obj.put("data", array);
        obj.put("nbAmis", nbAmis);
        return obj;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected String extractNombreAmis(String id) throws MalformedURLException, IOException {
        WebPageExtractor extractor = new WebPageExtractor(new URL("http://friends.myspace.com/index.cfm?fuseaction=user.viewfriends&friendID=" + id));
        extractor.extract();
        String[] tab2;
        for (Span span : extractor.getSpans()) {
            if (span.getSpanClass() != null && span.getSpanClass().equals("pgerTitle")) {
                tab2 = span.getSpanContent().split("sur ");
                if (tab2.length > 1) {
                    return tab2[1];
                }
            }
        }
        return "";
    }
}
