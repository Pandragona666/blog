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
<div class="container col-6">
    <a href="article?action=index">Main</a>
</div>
<div class = "container col-3">

    <table>
        <tr class = "table">
            <th>
                email
            </th>
            <th>
                nick
            </th>
            <th>
                registered
            </th>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>
                    <a href="/blog_war/user?action=view&id=${user.id}">${user.id}</a>
                </td>
                <td>
                    ${user.nick}
                </td>
                <td>
                    ${user.registered}
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
