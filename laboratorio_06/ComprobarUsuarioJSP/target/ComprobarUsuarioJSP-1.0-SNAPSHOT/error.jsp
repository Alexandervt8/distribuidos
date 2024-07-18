<%-- 
    Document   : error
    Created on : 10 jul. 2024, 22:14:55
    Author     : Alexander Valdiviezo / Fatimma Rojas / Melvin Castro
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Ocurrió un error</h1>
    <ul>
        <%
            String[] messages = (String[]) request.getAttribute("messages");
            if (messages != null) {
                for (String message : messages) {
                    out.println("<li>" + message + "</li>");
                }
            }
        %>
    </ul>
</body>
</html>

