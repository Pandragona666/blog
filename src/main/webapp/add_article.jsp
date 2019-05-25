<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
    <div class="container col-6">
        <h1>
            Dodaj artykuł do bloga
        </h1>
    </div>
    <div class="container col-6">
        <form action="/blog_war/article?action=add" method="post">
            <div class="form-group">
                <input class="form-control mb-3" type="text" name = "title" placeholder="wpisz tytuł"/>
                <textarea class="form-control mb-3" name="content" placeholder="treść"></textarea>
                <button class="form-control mb-3" type="submit">Dodaj</button>
            </div>
        </form>
    </div>
    <div class="container col-6">
        <a href="article?action=index">Main</a>
    </div>
</body>
</html>
