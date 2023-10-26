<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test</title>
</head>
<body>
<h1><%= "Test!" %>
</h1>
<%
    String success =  (String)request.getParameter("success");
    if(success !=null ) {
%>
<h1>success</h1>
<%} %>






<form method="post" action="Servlets.ReportServlet">
    <button type="submit" class="btn btn-primary mx-3">Generate report</button>
</form>




<a href="PdfDownloadServlet">Download pfg</a>

<br/>

</body>
</html>