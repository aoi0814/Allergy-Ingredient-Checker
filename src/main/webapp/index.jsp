<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>アレルギー食材チェッカー</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Reggae+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style_index.css'/>">
        
</head>
<body>
    <header>
        <h1>アレルギー食材チェッカー</h1>
    </header>
    <main>
        <section id="description">
            <h2>このシステムについて</h2>
            <p>アレルギーを持つ方やその家族、飲食店スタッフのために、料理に含まれるアレルギー食材を確認し、代替案やアレルギー症状を簡単にチェックできます。</p>
            <p><strong>使い方:</strong> 以下のフォームに料理名を入力して、検索してください。</p>
        </section>
        <form action="<c:url value='/search'/>" method="post">
            <label for="dish">料理名を入力してください:</label>
            <input type="text" id="dish" name="dish" placeholder="例: カレーライス,うどん,アイスクリーム,トースト,天ぷら,ドーナツ,チョコレートケーキ,しゃぶしゃぶ,シェイク,クラムチャウダー,お好み焼き,ラーメン,モンブラン,ピザ,パン,ハンバーガー,トースト" required>
            <input type="submit" value="検索">
        </form>
                
<section id="features">
    <div class="features-container">
        <div class="features-text">
            <h3>主な機能</h3>
            <ul>
                <li>料理に含まれるアレルギー食材の表示</li>
                <li>代替食材の提案</li>
                <li>アレルギー症状の確認</li>
            </ul>
        </div>
        <img src="<c:url value='/images/features_image.png'/>" alt="機能説明画像" class="features-image">
    </div>
</section>

    </main>
    <script src="<c:url value='/js/script_index.js'/>"></script>
    
    </body>
</html>
