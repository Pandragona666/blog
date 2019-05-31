<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="head.jspf"%>
<body>
    <div class="container">
        <h2>Hello World!</h2>
    </div>
    <div class="container">
        <nav>
            <ul>
                <a href="article?action=viewAll">Lista artykułów</a>
            </ul>
            <ul>
                <a href="article?action=viewShortList">Okrojona lista artykułów</a>
            </ul>
            <ul>
                <a href="article?action=add">Dodaj</a>
            </ul>
            <ul>
                <a href="user?action=add">Zarejestruj się</a>
            </ul>
            <ul>
                <a href="user?action=viewAll">Użytkownicy</a>
            </ul>
        </nav>
    </div>
</body>
</html>
