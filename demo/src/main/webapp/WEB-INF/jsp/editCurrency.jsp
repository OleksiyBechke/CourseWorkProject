<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редагування валюти</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editCurrency.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&display=swap" rel="stylesheet">
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/favicon/favicon-16x16.png">
    <link rel="manifest" href="${pageContext.request.contextPath}/favicon/site.webmanifest">
</head>
<body>

<div class="font">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <form id="redirectOnIndexForm" action="admin" method="GET">
                <input type="hidden" name="command" value="redirectOnIndex">
            </form>
            <a class="navbar-brand" href="#" onclick="document.getElementById('redirectOnIndexForm').submit()">
                <img src="${pageContext.request.contextPath}/images/message-dollar.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                Currency</a>
            <div class="collapse navbar-collapse font" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link active font" aria-current="page">Редагування</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="logout">Log out</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<form action="admin" method="GET">
    <div class="font">
        <input type="hidden" name="command" value="redirectOnСurrenciesPage">
        <button id="back" type="submit" class="btn btn-outline-danger" value="index.jsp">Back</button>
    </div>
</form>

<h1 id="edit" class="font">Редагування назви валюти</h1>

<div id="edit-form">
    <form class="font" action="admin" method="POST">
        <div class="input-group mb-3 select flex-nowrap">
        <input type="hidden" name="command" value="saveCurrency">
        <input class="font" type="hidden" name="currencyId" value="${currency.id}">
            <span class="input-group-text font" id="basic-addon">Назва валюти:</span> <input id="edit-input" type="text" class="form-control font" name="currencyName" value="<c:out value="${currency.name}"/>">
        <input class="btn btn-warning font" type="submit" value="Редагувати">
        </div>
    </form>
</div>
</body>
</html>
