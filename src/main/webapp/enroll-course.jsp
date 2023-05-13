<%--
  Created by IntelliJ IDEA.
  User: Ibrah
  Date: 2022-11-19
  Time: 9:29 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Assignment 1</title>
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
    <h1 class="top-title">Enroll to your classes!</h1>
</div>
<!-- Main -->
<div class="main">
    <div class="main-register">
        <!-- A student can add more than 1 class at a time -->
        <h2 class="main-register-title">
            Enter your student id and the course you wish to add
        </h2>

        <form class="main-register-info" action="${pageContext.request.contextPath}/enroll-course" method="post">
            <p class="main-register-info-id">
                <label>Student ID</label>
                <input
                        class="main-register-info-id-input"
                        name="studentID"
                        type="text"
                        value=""
                        size="16"
                        maxlength="10"
                        placeholder="Enter student ID"
                />
            </p>
            <p class="main-register-info-course-code">
                <label>Course code</label>
                <input
                        class="main-register-info-course-code-input"
                        name="courseCode"
                        type="text"
                        value=""
                        size="16"
                        placeholder="Enter course code"
                />
            </p>
            <button  class="main-register-info-submit">Register</button>
            <span class="main-register-info-submit-span">
            All info looks good!
          </span>

            <div class="main-registerclasses">
                <a href="./index.jsp"
                ><h1 class="main-registerclasses-title">Registeration page</h1></a
                >
            </div>
            <div class="main-registerclasses">
                <a href="./drop-course.jsp"
                ><h1 class="main-registerclasses-title">Drop Course</h1></a
                >
            </div>
        </form>
    </div>

    <div class="main-information">
        <h2 class="main-information-title">Additional Information</h2>
        <p>
           What calls do you want to enroll in next.
        </p>
    </div>
</div>
<script src="enroll-course.js"></script>
</body>
</html>

