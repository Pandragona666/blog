<%--
  Created by IntelliJ IDEA.
  User: korda
  Date: 26.05.2019
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
    <div class="container col-6">
        <h1>
            Edytuj artykuł bloga
        </h1>
    </div>
    <div class="container col-6">
        <form action="/blog_war/article?action=update&id=${requestScope.article.id}" method="post">
            <div class="form-group">
                <inputHidden type="hidden" name="id" value="${requestScope.article.id}"/>
                <input class="form-control mb-3" type="text" name = "title" value="${requestScope.article.title}"/>
                <textarea class="form-control mb-3" disabled name="content" placeholder="treść">
                    ${requestScope.article.content}
                </textarea>
                <button class="form-control mb-3" type="submit">Change</button>
            </div>
        </form>
    </div>
    <div class="container col-6">
        <a href="article?action=index">Main</a>
    </div>
</body>
</html>
