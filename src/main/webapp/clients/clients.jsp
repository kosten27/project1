<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Clients</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>

    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Webapp</h1>
        </div>

        <div class="w3-container w3-center w3-margin-bottom w3-padding">
            <div class="w3-card-4">
                <div class="w3-container w3-light-blue">
                    <h2>Clients</h2>
                </div>
                <div class="w3-container w3-center">
                    <div class="w3-bar w3-padding-large w3-padding-24">
                        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/clients/addClient.jsp'">Add</button>
                        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/clients/modifyClient.jsp'">Modify</button>
                        <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='/clients/deleteClient.jsp'">Delete</button>
                    </div>
                </div>
                <%
                    List<String> clients = (List<String>) request.getAttribute("clients");

                    if (clients != null && !clients.isEmpty()) {
                        out.println("<ul class=\"w3-ul\">");
                        for (String client : clients) {
                            out.println("<li class=\"w3-hover-sand\">" + client + "</li>");
                        }
                        out.println("</ul>");

                    } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
+
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                            "   <h5>There are no clients yet!</h5>\n" +
                            "</div>");
                %>
            </div>
        </div>

        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
            <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
        </div>
    </body>
</html>