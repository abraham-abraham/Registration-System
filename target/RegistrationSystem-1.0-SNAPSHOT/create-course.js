let form = document.querySelector(".main-register-info");
let submit = document.querySelector(".main-register-info-submit");
let id = document.querySelector(".main-register-info-id-input");
let courseCode = document.querySelector(
    ".main-register-info-course-code-input"
);
let courseTitle = document.querySelector(".main-register-info-title-input");
let semester = document.querySelector(".main-register-info-semester-input");
let days = document.querySelector(".main-register-info-days-input");
let courseTime = document.querySelector(".main-register-info-courseTime-input");
let instructor = document.querySelector(".main-register-info-instructor-input");
let startDate = document.querySelector(".main-register-info-startDate-input");
let endDate = document.querySelector(".main-register-info-endDate-input");
let room = document.querySelector(".main-register-info-room-input");


const isnum = (val) => /^\d+$/.test(val);

const onFormChange = () => {
    let startDateObject = new Date(startDate.value);
    let endDateObject = new Date(endDate.value);

    if (startDateObject.getTime() > endDateObject.getTime()) {
        alert("Start date is after end date. Please adjust accordingly");
        startDate.value = "";
        endDate.value = "";
    }

    submit.disabled = !(
        id.value &&
        isnum(id.value) &&
        courseCode.value &&
        semester.value &&
        days.value &&
        courseTime.value &&
        courseTitle.value &&
        instructor.value &&
        room.value &&
        startDate.value &&
        endDate.value
    );
};

form.addEventListener("change", onFormChange);
form.addEventListener("keyup", onFormChange);