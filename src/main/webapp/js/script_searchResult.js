document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll("input[type='checkbox']");
    const formButtons = document.querySelectorAll("input[type='button']");
    const toggleCheckboxButton = document.getElementById("toggleCheckboxButton");

    // ボタンにクリックエフェクトを追加
    formButtons.forEach(button => {
        button.addEventListener("mousedown", () => {
            button.style.transform = "scale(0.95)";
        });
        button.addEventListener("mouseup", () => {
            button.style.transform = "scale(1)";
        });
    });

    // 全選択/全解除ボタンの動作
    if (toggleCheckboxButton) {
        toggleCheckboxButton.addEventListener("click", () => {
            checkboxes.forEach(checkbox => {
                checkbox.checked = !checkbox.checked;
            });
        });
    }
});

// validateAndSubmit 関数
function validateAndSubmit(action) {
    // チェックボックスが選択されているか確認
    const checkboxes = document.querySelectorAll('input[name="allergens"]:checked');
    if (checkboxes.length === 0) {
        // 警告を表示
        alert('アレルギーを選択してください。');
        return;
    }

    // hidden input を作成してアクションをフォームに追加
    const form = document.getElementById("allergenForm");
    form.action = action;
    form.submit();
}

