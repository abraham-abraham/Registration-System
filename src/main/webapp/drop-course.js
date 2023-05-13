let form = document.querySelector(".main-drop-info");
let studentIdInput = document.querySelector(".main-drop-info-id-input");
let courseCodeInput = document.querySelector(
    ".main-drop-info-course-code-input"
);
let submit = document.querySelector(".main-drop-info-submit");
let submitValidation = document.querySelector(".main-drop-info-submit-span");

const isnum = (val) => /^\d+$/.test(val);

function eventHandler() {
    if (
        studentIdInput.value &&
        isnum(studentIdInput.value) &&
        courseCodeInput.value
    ) {
        submit.disabled = false;
        submitValidation.style.display = "inline";
    } else {
        submit.disabled = true;
        submitValidation.style.display = "none";
    }
}

form.addEventListener("keyup", eventHandler);
