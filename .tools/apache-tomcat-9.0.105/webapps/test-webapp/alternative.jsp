<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>代替案</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_alternative.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Reggae+One&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <h1>代替案</h1>
</header>
<main>
    <!-- alternative2が正しく渡されている場合、表示される -->
    <p>${alternative2}</p>

    <!-- alternativeがリンクの場合、クリック可能なリンクとして表示 -->
    <p>
        <a href="${alternative}" target="_blank">${alternative}</a>
    </p>

    <!-- トップページに戻るリンク -->
    <a href="${pageContext.request.contextPath}">トップページに戻る</a>
</main>
</body>
</html>
