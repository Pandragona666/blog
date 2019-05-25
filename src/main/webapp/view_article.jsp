<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
    <div class="container col-6">
        <h1>
            ${requestScope.article.title}
        </h1>
        <p>
            ${requestScope.article.content}
        </p>
    </div>
    <div>
        <a href="article?action=index">Main</a>
    </div>
</body>
</html>