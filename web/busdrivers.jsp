<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProjektMPKWeb.*"%>  
<!DOCTYPE html>
<%
    DriversDAO drivers = null;
    String idStr = "";
    ServletContext c = session.getServletContext();
    //firstrun1 has to be named different for every table
    
    if (c.getAttribute("firstRun1") == null) {
        drivers = new DriversDAO();
        idStr = String.valueOf(drivers.id);
        c.setAttribute("firstRun1", 1);
        c.setAttribute("drivers", drivers);
    } else {
        drivers = (DriversDAO)c.getAttribute("drivers");
        idStr = String.valueOf(drivers.id);
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
	
            surnameOnInput = function() {
		document.getElementsByName("delete")[0].disabled = true;
		document.getElementsByName("delete")[0].style.backgroundColor = "gray";
            };
	
            sexOnInput = function() {
		document.getElementsByName("delete")[0].disabled = true;
		document.getElementsByName("delete")[0].style.backgroundColor = "gray";
            };
	
            electricOnInput = function() {
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
            <HEADER style="top: 8px">Kierowcy Autobusow</HEADER>
            <FORM method="post" action="Drivers">
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
                        value="<%= drivers.name %>"
                        oninput="nameOnInput()"
                    >

                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 56px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Surname:</FONT>
                    </DIV>
                    <INPUT
                        name="surname"
                        type="text"
                        style="box-sizing: border-box; width: 208px; height: 20px; position: absolute; left: 80px; top: 56px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= drivers.surname %>"
                        oninput="surnameOnInput()"
                    >

                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 80px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Sex:</FONT>
                    </DIV>
                    <INPUT
                        name="sex"
                        type="text"
                        style="box-sizing: border-box; width: 208px; height: 20px; position: absolute; left: 80px; top: 80px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= drivers.sex %>"
                        oninput="sexOnInput()"
                    >

                    <DIV style="width: 64px; height: 20px; position: absolute; left: 8px; top: 104px; text-align: left;">
                        <FONT style="font-family: sans-serif; font-size: 8pt">Level:</FONT>
                    </DIV>
                    <INPUT
                        name="electric"
                        type="text"
                        style="box-sizing: border-box; width: 208px; height: 20px; position: absolute; left: 80px; top: 104px; font-family: sans-serif; font-size: 8pt;"
                        value="<%= drivers.electric %>"
                        oninput="electricOnInput()"
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
