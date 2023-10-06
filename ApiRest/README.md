

Rate-Limited Notification Service
---
~~~
The task
We have a Notification system that sends out email notifications of various types (supdatesupdate, daily news, project invitations, etc). 
We need to protect recipients from getting too many emails, either due to system errors or due to abuse, so let's limit the number of emails sent to them by implementing a rate-limited version of NotificationService.
The system must reject requests that are over the limit.

Some sample notification types and rate limit rules, e.g.:
Status: not more than 2 per minute for each recipient
News: not more than 1 per day for each recipient
Marketing: not more than 3 per hour for each recipient

Etc. these are just samples, the system might have several rate limit rules!

NOTES:

Your solution will be evaluated on code quality, clarity and development best practices.

Feel free to use the programming language, frameworks, technologies, etc that you feel more comfortable with.

Below you'll find a code snippet that can serve as a guidance of one of the implementation alternatives in Java. Feel free to use it if you find it useful or ignore it otherwise; it is not required to use it at all nor translate this code to your programming language.
~~~
----

Introduction
---
Welcome to the Modak Technical Exercise project repository! This project is developed using Java and Spring.

---
Dependencies
---
The project currently has the following dependencies:

Java 17, Gson, Spring Framework 3, springdoc, Bucket4j

---


API
---

Send Message:
--

Method: POST

URL: {{url}}/api/send

Headers:

userId:{{userId}}

Notification-Type:{{type}}

(The headers are mandatory; these values are used for the rate limiter.)

Request:
~~~
{
    "message":"This is a status message test."
}
~~~

response:
~~~
{
    "timestamp": 1696548672126,
    "status": "200",
    "response": "\"sending message to user 1\""
}
~~~


Optional Improvements
---

Builders: Implement builders instead of constructors.

Lombok Integration: Consider integrating Lombok to reduce repetitive code in the project.

Swagger Documentation: you can view it with the basic configuration at: http://localhost:8080/swagger-ui/index.html
