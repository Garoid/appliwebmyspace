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
    </head>
    <body>
        <h2>Utilisation de l'API MySpace</h2>
        <form method="post" action="Extraction">
            URL : <input type="text" name="url" /><br />
            Nom du profil MySpace : <input type="text" name="profil" /><br />
            Profondeur des amis : <select name="profondeur">
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
            </select><br />
            <input type="submit" value="Envoyer" />
        </form>
    </body>
</html>
