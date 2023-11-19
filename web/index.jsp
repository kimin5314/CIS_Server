<%--
  Created by IntelliJ IDEA.
  User: Kimin
  Date: 11/9/2023
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
$END$
<div>
    <button onclick="test()">test</button>
</div>
</body>
<script>
    function test() {
        fetch("http://kimin.cn:8081/CIS_Server_war/course", {
            method: "GET",
        })
            .then(res => res.text())
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.log(err)
            })
    }
</script>
</html>
