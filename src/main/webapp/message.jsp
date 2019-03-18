<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add client</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>

    <body class="w3-light-grey">
        <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
            <h1>Webapp</h1>
        </div>

        <div class="w3-container w3-padding">
                <%
                    if (request.getAttribute("message") != null) {
                        out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n" +
                                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                                "   <h5>" + request.getAttribute("message") + "</h5>\n" +
                                "</div>");
                    }
                %>
        </div>

        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
            <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
        </div>
    </body>
</html>