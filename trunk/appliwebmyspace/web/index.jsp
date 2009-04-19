<%-- 
    Document   : index
    Created on : 10 mars 2009, 16:23:24
    Author     : jasonleborgne
--%>
<%@page import="java.net.URL;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utilisation de l'API MySpace</title>
        <link rel=stylesheet type="text/css" href="style.css">
        <script type="text/javascript" src="js/excanvas.js"></script>
        <script type="text/javascript" src="js/mootools-1.2.js" ></script>
        <script type="text/javascript" src="js/RGraph.js" ></script>
        <script type="text/javascript" src="js/testAffichage.js" ></script>
    </head>
    <body onload="sentInfo();">
        <div id="wrap">
            <div id="header">
                <h1><a href="#">GraphSpace</a></h1>
                <h2>Represent your MySpace friends network...</h2>
            </div>
            <div id="content">
                <h2><a href="#">Graph</a></h2>
                <div id="myspaceGraph"></div>
                <div id="infovis"></div>
                <div id="tempsTraitement"></div>
            </div>

            <div id="rightmenu">
                <h2>Enter your informations</h2>
                <ul>
                    <form method="post" action="#">
                        <li><a href="#">ID of profile<br></a>
                        <input type="text" name="id" id="idInput" 
                        <%
                        if(request.getParameter("id")!=null)
                            out.print("value=\""+request.getParameter("id")+"\" ");
                        %>/></li><br />
                        <li><a href="#">Number of friends</a></li>
                        <li><select name="profondeur" id="profSelect">
                            <%
                                for(int i=1;i<=10;i++){
                                    out.print("<option value=\""+i+"\" ");
                                    if(request.getParameter("prof")!=null && i==Integer.parseInt(request.getParameter("prof")))
                                        out.print("selected ");
                                    else if(i==2)
                                        out.print("selected ");
                                    out.println(">"+i+"</option>");

                                }
                            %>
                            </select>
                        </li><br />
                        <li><a href="#">Friends depth</a></li>
                        <li><select name="niveau" id="niveauSelect">
                            <%
                                for(int i=1;i<=3;i++){
                                    out.print("<option value=\""+i+"\" ");
                                    if(request.getParameter("niveau")!=null && i==Integer.parseInt(request.getParameter("niveau")))
                                        out.print("selected ");
                                    else if(i==1)
                                        out.print("selected ");
                                    out.println(">"+i+"</option>");

                                }
                            %>
                            </select>
                        </li><br />
                        <li><input type="button" value="Envoyer" id="btnClick" onclick="init(this.form.id.value,this.form.profondeur,this.form.niveau); return false;"/></li>
                    </form>
                    <div id="infos"></div>
                </ul>
            </div>

            <div style="clear: both;"> </div>

            <div id="footer">
            </div>

        </div>
    </body>
</html>
