/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import fr.inria.edelweiss.extractor.webpage.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT
            // 31427788 ID ARMAND GUEIT

            if (request.getParameter("id") != null) {
                WebPageExtractor extractor = new WebPageExtractor(new URL("http://profile.myspace.com/index.cfm?fuseaction=user.viewProfile&friendID=" + request.getParameter("id")));
                extractor.extract();
                    
                if (!extractor.getTitle().equalsIgnoreCase("Invalid Friend ID")) {
                    createXML(extractor);
                    out.println("Fichier profil.xml a été créé avec succès" + "<br />");
                    out.println("===========================================" + "<br />");
                    out.println("Tous les class des div" + "<br />");
                    out.println("===========================================" + "<br />");
                    for (Div div : extractor.getDivs()){
                        out.println(div.getDivClass() + "<br />");
                    }
                    out.println("Tous les id des table" + "<br />");
                    out.println("===========================================" + "<br />");
                    for (Table table : extractor.getTables()){
                        out.println(table.getTableClass() + "<br />");
                    }
                } else {
                    out.println("L'ID " + request.getParameter("id") + " ne correspond à aucun profil dans MySpace...");
                }


            //ContentBlock cb = new ContentBlock();
            // show internal structure
            //out.println(extractor.toString());
            // show extracted text
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void createXML(WebPageExtractor extractor) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        Document document = null;
        DocumentBuilderFactory factory = null;
        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();

        Element racine = (Element) document.createElement("profil");
        document.appendChild(racine);

//        Element friends = (Element) document.createElement("friends");
//        racine.appendChild(friends);

//        for (Div div : extractor.getDivs()){
//            System.out.println("DIV CLASS : "+div.getDivClass());
//            if (div.getDivClass().equals("userComment")){
//                Element friend = (Element) document.createElement("friend");
//                friends.appendChild(friend);
//            }
//
//        }

        TransformerFactory fact = TransformerFactory.newInstance();
        Transformer transf = fact.newTransformer();
        //transf.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transf.transform(new DOMSource(document), new StreamResult("/Users/guillaume/NetBeansProjects/appliwebmyspace/profil.xml"));
    }

    public static void parcourir(Node node) {
        for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node.getNodeName().equalsIgnoreCase("nom")) {
                System.out.println("nom : " + node.getTextContent());
            }
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (((Element) node).getAttributeNode("date-naissance") != null) {
                }
            }
            parcourir(node);
        }
    }
}
