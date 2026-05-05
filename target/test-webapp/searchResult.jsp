<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>検索結果</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Reggae+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_searchResult.css">

    
    <script src="${pageContext.request.contextPath}/js/script_searchResult.js"></script>
    <script>
    

    function changeImage1(imageName) {
        const image = document.getElementById('dynamic-image');
        image.src = '${pageContext.request.contextPath}/images/left.png';
        image.style.borderColor = "#ccc"; // ホバー時の枠線色
    }

    function changeImage2(imageName) {
        const image = document.getElementById('dynamic-image');
        image.src = '${pageContext.request.contextPath}/images/light.png';
        image.style.borderColor = "#ccc"; // ホバー時の枠線色
    }

    function resetImage() {
        const image = document.getElementById('dynamic-image');
        image.src = '${pageContext.request.contextPath}/images/features_image.png';
        image.style.borderColor = "#ccc"; // 元の枠線色
    }
        
    </script>
</head>
<body>
    <header>
        <h1>検索結果: ${dish}</h1>
    </header>
    <main>
        <section id="user-guide">
            <p>以下のリストから該当するアレルギー食材を選択してください。選択した後に、「症状を表示」または「代替案を表示」ボタンをクリックしてください。</p>
        </section>

        <!-- アレルギー情報フォーム -->
        <form id="allergenForm" method="post">
            <input type="hidden" name="dish" value="${dish}">
            <section id="allergen-list">
                <h2>アレルギー食材:</h2>
                <c:forEach items="${allergens}" var="allergen">
                    <label>
                        <input type="checkbox" name="allergens" value="${allergen}"> ${allergen}
                    </label><br>
                </c:forEach>
            </section>

            <!-- アクションボタン -->
            <section id="action-buttons">
            <div class="button-container">
    <button 
        type="button" 
        class="image-button" 
        onmouseover="changeImage1('left.png')" 
        
        onclick="validateAndSubmit('symptoms')">症状を表示</button>
        
    <button 
        type="button" 
        class="image-button" 
        onmouseover="changeImage2('light.png')" 
        onclick="validateAndSubmit('alternative')">代替案を表示</button>
        </div>
			</section>

<!-- サイトの最後に表示する画像枠 -->
<div id="image-frame">
    <img id="dynamic-image" src="${pageContext.request.contextPath}/images/features_image.png" alt="画像表示枠">
</div>

        </form>

        <!-- 全選択/全解除ボタン -->
        <button type="button" id="toggleCheckboxButton">全選択/全解除</button>
                
    </main>
</body>
</html>
