<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProjektMPKWeb.*"%>  
<!DOCTYPE html>
<%
    TramsDAO tram = null;
    String idStr = "";
    ServletContext c = session.getServletContext();
    if (c.getAttribute("firstRun3") == null) {
        tram = new TramsDAO();
        idStr = String.valueOf(tram.id);
        c.setAttribute("firstRun3", 1);
        c.setAttribute("tram", tram);
    } else {
        tram = (TramsDAO)c.getAttribute("tram");
        idStr = String.valueOf(tram.id);
    }
%>
<HTML>
    <HEAD>
        <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE>ProjektMPKWeb</TITLE>
        <SCRIPT type="text/javascript">
            nameOnInput = function() {
		document.getElementsByName("delete")[0].disabled = true;
		document.getElementsByName("delete")[0].style.backgroundColor = "gray";
            };
	
            levelOnInput = function() {
		document.getElementsByName("delete")[0].disabled = true;
		document.getElementsByName("delete")[0].style.backgroundColor = "gray";
            };

            createOnClick = function() {
                document.getElementsByName("action")[0].value = "create";
                document.getElementsByName("idValue")[0].value = document.getElementsByName("id")[0].value;
            };
	
            updateOnClick = function() {
                document.getElementsByName("action")[0].value = "update";
                document.getElementsByName("idValue")[0].value = document.getElementsByName("id")[0].value;
            };

            deleteOnClick = function() {
                document.getElementsByName("action")[0].value = "delete";
                document.getElementsByName("idValue")[0].value = document.getElementsByName("id")[0].value;
            };

            previousOnClick = function() {
                document.getElementsByName("action")[0].value = "previous";
                document.getElementsByName("idValue")[0].value = document.getElementsByName("id")[0].value;
            };
	
            nextOnClick = function() {
                document.getElementsByName("action")[0].value = "next";
                document.getElementsByName("idValue")[0].value = document.getElementsByName("id")[0].value;
            };
        </SCRIPT>
    </HEAD>
    <BODY background="images\texture.jpg">
        <ul style="list-style-type: none; margin: 0; padding: 0;">
            <li><a href="index.jsp">Motorniczy</a></li>
            <li><a href="busdrivers.jsp">Kierowcy Autobusow</a></li>
            <li><a href="buses.jsp">Autobusy</a></li>
            <li><a href="trams.jsp">Tramwaje</a></li>
        </ul>
        <DIV style="width: 312px; height: 328px; background-color: rgb(255,255,255); position: absolute; left: 0; top: 0; right: 0; bottom: 0; margin: auto;">
            <HEADER style="top: 8px">Tramwaje</HEADER>
            <FORM method="post" action="Trams">
                <INPUT name="action" type="hidden">
                <INPUT name="idValue" type="hidden">
                <DIV style="width: 296px; height: 256px; border-style: solid; border-width: 1px; border-color: rgb(0,0,0); position: absolute; left: 8px; top: 32px;">
                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 8px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Id:</FONT>
                    </DIV>
                    <INPUT
                        name="id"
                        type="text"
                        style="box-sizing: border-box; width: 72px; height: 20px; position: absolute; left: 80px; top: 8px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= idStr %>"
                        disabled
                    >

                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 32px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Name:</FONT>
                    </DIV>
                    <INPUT
                        name="name"
                        type="text"
                        style="box-sizing: border-box; width: 208px; height: 20px; position: absolute; left: 80px; top: 32px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= tram.name %>"
                        oninput="nameOnInput()"
                    >

                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 56px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Level:</FONT>
                    </DIV>
                    <INPUT
                        name="level"
                        type="text"
                        style="box-sizing: border-box; width: 208px; height: 20px; position: absolute; left: 80px; top: 56px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= tram.level %>"
                        oninput="levelOnInput()"
                    >

                    <INPUT
                        name="create"
                        type="submit"
                        value="Create"
                        style="width: 72px; height: 24px; position: absolute; left: 8px; top: 224px; background-color: green; color: white; font-family: sans-serif; font-size: 8pt;"
                        onclick="createOnClick()"
                    >
                    <INPUT
                        name="update"
                        type="submit"
                        value="Update"
                        style="width: 72px; height: 24px; position: absolute; left: 112px; top: 224px; background-color: green; color: white; font-family: sans-serif; font-size: 8pt;"
                        onclick="updateOnClick()"
                    >
                    <INPUT
                        name="delete"
                        type="submit"
                        value="Delete"
                        style="width: 72px; height: 24px; position: absolute; left: 216px; top: 224px; background-color: green; color: white; font-family: sans-serif; font-size: 8pt;"
                        onclick="deleteOnClick()"
                    >
                </DIV>
                <INPUT
                    name="previous"
                    type="submit"
                    value="◄"
                    style="width: 72px; height: 24px; position: absolute; left: 8px; top: 296px; background-color: green; color: white; font-family: sans-serif; font-size: 8pt;"
                    onclick="previousOnClick()"
                >
                <INPUT
                    name="next"
                    type="submit"
                    value="►"
                    style="width: 72px; height: 24px; position: absolute; left: 232px; top: 296px; background-color: green; color: white; font-family: sans-serif; font-size: 8pt;"
                    onclick="nextOnClick()"
                >
            </FORM>
        </DIV>
    </BODY>
</HTML>
