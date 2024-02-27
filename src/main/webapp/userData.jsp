<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Data</title>
</head>
<body>

    <h2>User Data</h2>

    <c:if test="${sessionScope.userModel ne null and sessionScope.isAuthenticated}">
        <table border='1'>
            <tr>
                <th>ID</th>
                <th>Age</th>
                <th>Name</th>
                <th>Email</th>
            </tr>
            <tr>
                <td><c:out value="${sessionScope.userModel.id}" /></td>
                <td><c:out value="${sessionScope.userModel.age}" /></td>
                <td><c:out value="${sessionScope.userModel.name}" /></td>
                <td><c:out value="${sessionScope.userModel.email}" /></td>
            </tr>
        </table>
         <form action="logout" method="post">
            <input type="submit" value="Déconnexion">
        </form>
    </c:if>

</body>
</html>
