<%--
  Created by IntelliJ IDEA.
  User: korda
  Date: 25.05.2019
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<%@ include file="head.jspf"%>
<body>
    <div class = "container">
        <sql:setDataSource var="baza"
                           driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/blog?serverTimezone=UTC"
                           user="root"
                           password="Sm0cz0J3st"/>
        <sql:query var="articles" sql="SELECT * FROM article"/>
        <table>
            <tr>
                <th>
                    Tytuł
                </th>
            </tr>
            <c:forEach var="article" items="${articles}">
                <a href="/blog_war/article?id=${article.id}">${article.title}</a>
            </c:forEach>
        </table>

    </div>
</body>
</html>
