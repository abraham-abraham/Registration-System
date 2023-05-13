<%--
  Created by IntelliJ IDEA.
  User: Ibrah
  Date: 2022-11-19
  Time: 9:27 p.m.
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
  <h1 class="top-title">Create a course /</h1>
  <h1 class="top-title">Print reports</h1>
</div>
<!-- Main -->
<div class="main">
  <div class="main-register">
    <!-- A student can add more than 1 class at a time -->
    <h2 class="main-register-title">Enter information about the course</h2>

    <form class="main-register-info" action="${pageContext.request.contextPath}/create-course" method="post">
      <p class="main-register-info-id">
        <label>Employment ID</label>
        <input
                class="main-register-info-id-input"
                name="employmentID"
                type="text"
                value=""
                size="16"
                placeholder="Enter employment ID"
        />
      </p>
      <p class="main-register-info-course-code">
        <label>Course Code</label>
        <input
                class="main-register-info-course-code-input"
                name="courseCode"
                type="text"
                value=""
                size="16"
                placeholder="Enter Course Code"
        />
      </p>
      <p class="main-register-info-title">
        <label>Course Title</label>
        <input
                class="main-register-info-title-input"
                name="title"
                type="text"
                value=""
                size="16"
                placeholder="Enter Course Title"
        />
      </p>
      <p class="main-register-info-semester">
        <label>Semester</label>
        <input
                class="main-register-info-semester-input"
                name="semester"
                type="text"
                value=""
                size="16"
                placeholder="Enter Semester"
        />
      </p>
      <p class="main-register-info-days">
        <label>Days</label>
        <input
                class="main-register-info-days-input"
                name="days"
                type="text"
                value=""
                size="16"
                placeholder="Enter Days"
        />
      </p>
      <p class="main-register-info-courseTime">
        <label>Course Time</label>
        <input
                class="main-register-info-courseTime-input"
                name="courseTime"
                type="text"
                value=""
                size="16"
                placeholder="Enter Course Time"
        />
      </p>
      <p class="main-register-info-instructor">
        <label>Instructor</label>
        <input
                class="main-register-info-instructor-input"
                name="instructor"
                type="text"
                value=""
                size="16"
                placeholder="Enter Instructor"
        />
      </p>
      <p class="main-register-info-room">
        <label>Room</label>
        <input
                class="main-register-info-room-input"
                name="room"
                type="text"
                value=""
                size="16"
                placeholder="Enter Room"
        />
      </p>
      <p class="main-register-info-startDate">
        <label>Start Date</label>
        <input
                class="main-register-info-startDate-input"
                name="startDate"
                type="date"
                value=""
                size="16"
                placeholder="Enter Start Date"
        />
      </p>
      <p class="main-register-info-endDate">
        <label>End Date</label>
        <input
                class="main-register-info-endDate-input"
                name="endDate"
                type="date"
                value=""
                size="16"
                placeholder="Enter End Date"
        />
      </p>
      <button class="main-register-info-submit">Submit</button>

      <div class="main-registerclasses">
        <a href="./index.jsp">
          <h1 class="main-registerclasses-title">
            Registration page
          </h1>
        </a>
      </div>
    </form>
  </div>

  <div class="main-information">
    <h2 class="main-information-title">Reports</h2>
    <div class="main-information-student-list">
      <h3>List of Student in a certain course</h3>
      <form class="main-information-student-list-form" action="${pageContext.request.contextPath}/students-by-course" method="post">
        <input
                class="main-information-student-list-employmentID-input"
                name="employmentID"
                type="text"
                value=""
                size="16"
                placeholder="Enter employment ID"
        />
        <input
                class="main-information-student-list-course-code-input"
                name="courseCode"
                type="text"
                value=""
                size="16"
                placeholder="Enter Course Code"
        />
        <br />
        <button  class="main-information-student-list-submit">
          Submit
        </button>
      </form>
    </div>
    <div class="main-information-course-list">
      <h3>List of course of a certain student</h3>
      <form class="main-information-course-list-form" action="${pageContext.request.contextPath}/courses-by-student" method="post">
        <input
                class="main-information-course-list-employmentID-input"
                name="employmentID"
                type="text"
                value=""
                size="16"
                placeholder="Enter employment ID"
        />
        <input
                class="main-information-course-list-studentID-input"
                name="studentID"
                type="text"
                value=""
                size="16"
                placeholder="Enter studentID"
        />
        <br />
        <button class="main-information-course-list-form-submit">Submit</button>
      </form>
    </div>
  </div>
</div>
<script src="create-course.js"></script>
</body>
</body>
</html>
