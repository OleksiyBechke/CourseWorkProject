<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Курси обміну валют</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/exchangeRates.css">
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
                        <a href="#" class="nav-link active font" aria-current="page" onclick="document.getElementById('exchangeRatesForm').submit()">Курси обміну валют</a>
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
        <input type="hidden" name="command" value="redirectOnAdminPage">
        <button id="back" type="submit" class="btn btn-outline-danger" value="index.jsp">Back</button>
    </div>
</form>

<div id="exchange-form">
    <form action="admin" method="POST">

        <div id="input-currency" class="input-group mb-3 flex-nowrap">
            <input class="font" type="hidden" name="command" value="createExchangeRate">
            <span class="input-group-text font" id="basic-addon1">Оберіть валюту:</span>
            <select class="form-select font" name="currencyId">
                <c:forEach var="currency" items="${currencies}">
                    <c:choose>
                        <c:when test="${currencyId != null}">
                            <c:choose>
                                <c:when test="${currencyId == currency.id}">
                                    <option class="font" value="${currency.id}" selected><c:out value="${currency.name}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option class="font" value="${currency.id}"><c:out value="${currency.name}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <option class="font" value="${currency.id}"><c:out value="${currency.name}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div id="input-date" class="input-group mb-3 flex-nowrap">
            <span class="input-group-text font" id="basic-addon2">Оберіть день:</span>
            <select class="form-select font" name="dateId">
                <c:forEach var="date" items="${dates}">
                    <c:choose>
                        <c:when test="${dateId != null}">
                            <c:choose>
                                <c:when test="${dateId == date.id}">
                                    <option class="font" value="${date.id}" selected><c:out value="${currency.localDate}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <option class="font" value="${date.id}"><c:out value="${currency.localDate}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <option class="font" value="${date.id}"><c:out value="${date.localDate}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>


        <div id="input-rate" class="input-group mb-3 flex-nowrap">
            <span class="input-group-text font" id="basic-addon3">Курс обміну валюти:</span>
            <input class="form-control font" type="text" placeholder="Введіть поточний курс" name="exchangeRateUAH" required pattern="[0-9]*">
            <span class="input-group-text">₴</span>
            <input type="submit" class="btn btn-success" value="Додати">
        </div>

    </form>
</div>

<hr>

<table class="table table-success table-striped font">
    <thead>
    <tr>
        <th class="font">id</th>
        <th class="font">currencyId</th>
        <th class="font">dateId</th>
        <th class="font">exchangeRateUAH</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="exchangeRate" items="${exchangeRates}">
        <tr>
            <td class="font"><c:out value="${exchangeRate.id}"/></td>
            <td class="font"><c:out value="${currencyDao.findById(exchangeRate.currencyId).getName()}"/></td>
            <td class="font"><c:out value="${dateDao.findById(exchangeRate.dateId).getLocalDate()}"/></td>
            <td class="font"><c:out value="${exchangeRate.exchangeRateUAH}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
