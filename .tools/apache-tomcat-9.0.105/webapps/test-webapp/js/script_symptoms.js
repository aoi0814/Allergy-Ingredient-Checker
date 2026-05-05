// script.js
document.addEventListener("DOMContentLoaded", function () {
    const collapsibleElements = document.querySelectorAll(".collapsible");
    collapsibleElements.forEach(function (element) {
        element.addEventListener("click", function () {
            // Toggle the visibility of the next sibling element
            const nested = this.nextElementSibling;
            if (nested) {
                nested.classList.toggle("hidden");
            }
            // Toggle the arrow rotation
            const arrow = this.querySelector(".arrow");
            if (arrow) {
                arrow.classList.toggle("expanded");
            }
        });
    });
});
