<%--
  Created by IntelliJ IDEA.
  User: korda
  Date: 25.05.2019
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
    <h1>
        Dodaj artykuł do bloga
    </h1>
    <div class="container col-6">
        <form action="/article?action=add" method="post">
            <div class="form-group">
                <input class="form-control mb-3" type="text" name = "title" placeholder="wpisz tytuł"/>
                <textarea class="form-control mb-3" name="content" placeholder="treść"/>
                <button class="form-control mb-3" type="submit">Dodaj</button>
            </div>
        </form>
    </div>
</body>
</html>
