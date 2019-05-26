<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
<div class="container col-6">
    <h1>
        Rejestracja
    </h1>
</div>
<div class="container col-6">
    <form action="/blog_war/user?action=add" method="post">
        <div class="form-group">
            <input class="form-control mb-3" type="email" name = "email" placeholder="wpisz email"/>
            <input class="form-control mb-3" type="text" name = "nick" placeholder="wpisz nick"/>
            <input class="form-control mb-3" type="password" name = "password" placeholder="wpisz hasło"/>
            <input class="form-control mb-3" type="password" name = "repeatedPassword" placeholder="powtórz hasło"/>
            <button class="form-control mb-3" type="submit">Zarejestruj</button>
        </div>
    </form>
</div>
<div class="container col-6">
    <a href="article?action=index">Main</a>
</div>
</body>
</html>
