<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>アレルギー症状</title>
    <!-- Link the external CSS -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Reggae+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_symptoms.css">
</head>
<body>
<header>
    <h1>アレルギー症状</h1>
    </header>
    <main>
    <!-- Iterate through the outer map -->
    <c:forEach items="${symptoms}" var="entry">
        <div class="collapsible level-1">
            ${entry.key}
            <span class="arrow">→</span>
        </div>
        <div class="nested hidden">
            <!-- Iterate through the nested map -->
            <c:forEach items="${entry.value}" var="subEntry">
                <div class="collapsible level-2">
                    ${subEntry.key}
                    <span class="arrow">→</span>
                </div>
                <div class="nested hidden">
                    <!-- Iterate through the list -->
                    <c:forEach items="${subEntry.value}" var="symptom">
                        <div>・${symptom}</div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/">トップページに戻る</a>
    </main>
    <!-- Link the external JS -->
    <script src="${pageContext.request.contextPath}/js/script_symptoms.js"></script>
</body>
</html>
