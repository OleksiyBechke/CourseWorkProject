<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Веб-сайт з інформацією про курси валют</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
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

<div class="background-video">
    <video src="${pageContext.request.contextPath}/videos/video1.mp4" class="video_media" autoplay muted loop></video>
</div>



<div class="font">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <form id="redirectOnIndexForm" action="guest" method="GET">
                    <input type="hidden" name="command" value="redirectOnIndex">
            </form>
            <a class="navbar-brand" href="#" onclick="document.getElementById('redirectOnIndexForm').submit()">
                <img src="${pageContext.request.contextPath}/images/message-dollar.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                Currency</a>
            <div class="collapse navbar-collapse font" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="guest">Сторінка гостя</a>
                    </li>
                    <form id="adminForm" action="admin" method="GET">
                        <input type="hidden" name="command" value="redirectOnAdminPage">
                    </form>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#" onclick="document.getElementById('adminForm').submit()">Сторінка адміністратора</a>
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


<div id="welcome-message">
    <div id="welcome" class="font">
        <h1>Вітаємо на веб-сайті з інформацією про курси валют!</h1>
    </div>
    <br/>

    <h2 id="hello">Hello, <%=request.getRemoteUser()%></h2>
</div>

</body>



<%--<c:if test="${remoteUser != null}">--%>

<%--</c:if>--%>
</html>