<?xml version="1.0" encoding="UTF-8"?>
<%-- 
    Document   : index
    Created on : 10 mars 2009, 16:23:24
    Author     : jasonleborgneguillaumefoure
--%>
<%@page import="java.net.URL;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd"
     xml:lang="en" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>GraphSpace</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <script type="text/javascript" src="js/excanvas.js"></script>
        <script type="text/javascript" src="js/mootools-1.2.js" ></script>
        <script type="text/javascript" src="js/RGraph.js" ></script>
        <script type="text/javascript" src="js/mySpaceScript.js" ></script>
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
                <form method="post" action="#">
                <ul>
                        <li><a href="#">ID of profile</a><br />
                        <input type="text" name="id" id="idInput" 
                        <%
                        if(request.getParameter("id")!=null)
                            out.print("value=\""+request.getParameter("id")+"\" ");
                        %>/></li>
                        <li>&nbsp;</li>
                        <li><a href="#">Number of friends</a></li>
                        <li><select name="profondeur" id="profSelect">
                            <%
                                for(int i=1;i<=10;i++){
                                    out.print("<option value=\""+i+"\" ");
                                    if(request.getParameter("prof")!=null && i==Integer.parseInt(request.getParameter("prof")))
                                        out.print("selected=\"selected\" ");
                                    else if(i==4)
                                        out.print("selected=\"selected\" ");
                                    out.println(">"+i+"</option>");

                                }
                            %>
                            </select>
                        </li>
                        <li>&nbsp;</li>
                        <li><a href="#">Friends depth</a></li>
                        <li><select name="niveau" id="niveauSelect">
                            <%
                                for(int i=1;i<=3;i++){
                                    out.print("<option value=\""+i+"\" ");
                                    if(request.getParameter("niveau")!=null && i==Integer.parseInt(request.getParameter("niveau")))
                                        out.print("selected=\"selected\" ");
                                    else if(i==2)
                                        out.print("selected=\"selected\" ");
                                    out.println(">"+i+"</option>");

                                }
                            %>
                            </select>
                        </li>
                        <li>&nbsp;</li>
                        <li><a href="#">Get friends number ?</a></li>
                        <li><input type="checkbox" id="numberCheck" name="number" <%
                            if(request.getParameter("number")!=null)
                                out.print("value="+request.getParameter("number")+" ");
                        %>/></li>
                        <li><input type="button" value="Envoyer" id="btnClick" onclick="init(this.form.id.value,this.form.profondeur,this.form.niveau,this.form.number); return false;"/></li>
                    </ul>
                    </form>
                    <div id="infos"></div>
            </div>

            <div style="clear: both;"> </div>

            <div id="footer">
            </div>

        </div>
    </body>
</html>
