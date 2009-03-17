<%-- 
    Document   : index
    Created on : 10 mars 2009, 16:23:24
    Author     : jasonleborgne
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utilisation de l'API MySpace</title>
        <script type="text/javascript"
                src="http://ajax.googleapis.com/ajax/libs/dojo/1.2.3/dojo/dojo.xd.js"
                djConfig="parseOnLoad: true">
        </script>
    </head>
    <body>
        <h2>Utilisation de l'API MySpace</h2>
        <form method="post" onsubmit="">
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
        <%
        try {
			WebPageExtractor extractor = new WebPageExtractor(new URL("http://www.google.fr"));
			extractor.extract();
			// show internal structure
			out.println(extractor.toString());
			// show extracted text
			out.println("========================\n"+ extractor.fullText());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        %>

    </body>
</html>
