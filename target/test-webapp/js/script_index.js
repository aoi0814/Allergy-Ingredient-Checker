document.addEventListener("DOMContentLoaded", function() {
    const dishInput = document.getElementById("dish");
    const submitButton = document.querySelector("input[type='submit']");
    const suggestionBox = document.getElementById("suggestion-box");

    // 候補のデータ（例）
    const dishSuggestions = ["カレー", "ラーメン", "ハンバーグ", "オムライス", "サラダ"];

    // 自動候補の表示
    dishInput.addEventListener("input", function() {
        const input = dishInput.value.toLowerCase();
        suggestionBox.innerHTML = ""; // 前の候補をクリア
        if (input) {
            const suggestions = dishSuggestions.filter(dish => dish.toLowerCase().startsWith(input));
            suggestions.forEach(suggestion => {
                const suggestionItem = document.createElement("div");
                suggestionItem.classList.add("suggestion-item");
                suggestionItem.textContent = suggestion;
                suggestionItem.addEventListener("click", () => {
                    dishInput.value = suggestion;
                    suggestionBox.innerHTML = ""; // 候補をクリア
                });
                suggestionBox.appendChild(suggestionItem);
            });
        }
    });

    // 入力がないときに検索ボタンを無効化
    dishInput.addEventListener("input", function() {
        submitButton.disabled = !dishInput.value.trim();
    });

    // エラーメッセージの表示
    document.querySelector("form").addEventListener("submit", function(event) {
        if (!dishInput.value.trim()) {
            //event.preventDefault();
            alert("料理名を入力してください！");
        }
    });
});
