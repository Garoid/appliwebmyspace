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
        <script>
            
            /*/function envoiInfo(idM, profondeur){
                $.get("Extraction",{id:idM.value},function(data){
                    $("div#myspaceGraph").text(data);
                });
            }*/
            
        </script>
    </head>
    <body onload="init();">
        <div id="wrap">
            <div id="header">
                <h1><a href="#">GraphSpace</a></h1>
                <h2>Represent your MySpace friends network...</h2>
            </div>
            <div id="content">
                <h2><a href="#">Graph</a></h2>
                <div id="myspaceGraph"></div>
                <div id="infovis"></div>
            </div>

            <div id="rightmenu">
                <h2>Enter your informations</h2>
                <ul>
                    <form method="post" action="Extraction">
                        <li><a href="#">ID du profil MySpace</a>
                        <input type="text" name="id" /></li><br />
                        <li><a href="#">Profondeur des amis</a></li>
                        <li><select name="profondeur">
                                <option value="1" selected>1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                        </li><br />
                        <li><input type="submit" value="Envoyer" /></li>
                    </form>
                </ul>
            </div>

            <div style="clear: both;"> </div>

            <div id="footer">
            </div>

        </div>
    </body>
</html>
