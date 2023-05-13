<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Assignment 2</title>
    <link rel="stylesheet" href="style.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
            rel="stylesheet"
    />
</head>
<body>
<div class="top">
    <h1 class="top-title">Create your account</h1>
</div>
<!-- Main -->
<div class="main">
    <div class="main-register">
        <!-- A student can add more than 1 class at a time -->
        <h2 class="main-register-title">Enter your information</h2>
        <form class="main-register-info" action="${pageContext.request.contextPath}/register" method="post">
<%--        <form class="main-register-info">--%>
            <p class="main-register-info-role">
                <label>Role</label>
                <select name="role" class="main-register-info-role-select">
                    <option value="student">Student</option>
                    <option value="administrator">Administrator</option>
                </select>
            </p>
            <p class="main-register-info-firstName">
                <label>First Name</label>
                <input
                        class="main-register-info-firstName-input"
                        name="firstName"
                        type="text"
                        value=""
                        size="16"
                        placeholder="Enter first name"
                />
            </p>
            <p class="main-register-info-lastName">
                <label>Last Name</label>
                <input
                        class="main-register-info-lastName-input"
                        name="lastName"
                        type="text"
                        value=""
                        size="16"
                        placeholder="Enter last name"
                />
            </p>
            <p class="main-register-info-address">
                <label>Address</label>
                <input
                        class="main-register-info-address-input"
                        name="address"
                        type="text"
                        value=""
                        size="16"
                        placeholder="Enter address"
                />
            </p>
            <p class="main-register-info-email">
                <label>Email</label>
                <input
                        class="main-register-info-email-input"
                        name="email"
                        type="email"
                        value=""
                        size="16"
                        placeholder="Enter email"
                />
                <span class="main-register-info-email-validation">(valid!)</span>
            </p>
            <p class="main-register-info-phoneNumber">
                <label class="main-register-info-phoneNumber-text"
                >Phone Number</label
                >
                <input
                        class="main-register-info-phoneNumber-input"
                        name="phoneNumber"
                        type="tel"
                        value=""
                        size="16"
                        placeholder="Enter phone number"
                />
                <span class="main-register-info-phoneNumber-validation"
                >(valid!)</span
                >
            </p>
            <p class="main-register-info-dateOfBirth">
                <label>Date of birth</label>
                <input
                        class="main-register-info-dateOfBirth-input"
                        name="dateOfBirth"
                        type="date"
                        value=""
                        size="20"
                />
            </p>
            <button disabled  class="main-register-info-submit">Submit</button>
            <span class="main-register-info-submit-span">
            All info looks good!
          </span>
    <div class="main-registerclasses">
        <a href="./enroll-course.jsp"
        ><h1 class="main-registerclasses-title">Enroll courses</h1></a
        >
    </div>
    <div class="main-registerclasses">
        <a href="./drop-course.jsp"
        ><h1 class="main-registerclasses-title">Drop Course</h1></a
        >
    </div>
    <div class="main-registerclasses">
        <a href="./create-course.jsp"
        ><h1 class="main-registerclasses-title">Create Course</h1></a
        >
    </div>
        </form>

    </div>
</div>
<script src="register.js"></script>
</body>
</html>