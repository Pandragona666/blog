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
                           password="Sm0cz0J3st">
        </sql:setDataSource>
        <sql:query var="articles" dataSource="${baza}">
            SELECT * FROM article
        </sql:query>
        <table>
            <tr>
                <th>
                    Tytuł
                </th>
            </tr>
            <c:forEach var="article" items="${articles.rows}">
                <tr>
                    <td>
                        <a href="/blog_war/article?id=${article.id}">${article.title}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>
