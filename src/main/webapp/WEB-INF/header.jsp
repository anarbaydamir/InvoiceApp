<%--
  Created by IntelliJ IDEA.
  User: anarbaydamirov
  Date: 5/28/20
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">--%>
<%--    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>--%>
    <title>Digital Invoice</title>
</head>
<body>
<div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
    <a class="navbar-brand" href="invoice">Digitallife Invoice</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse show" id="navbarNav">
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="invoice">Invoice</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="projectreport">Report by Project</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="datereport">Report by Date</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="report">Report</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dictionary
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="client">Client</a>
                    <a class="dropdown-item" href="vendor">Vendor</a>
                    <a class="dropdown-item" href="project">Project</a>
                </div>
            </li>
        </ul>
    </div>
        <div style="margin-top: 10px">
        <form class="form-inline" action="logout" method="get">
                <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Log Out</button>
        </form>
        </div>
    </div>
</nav>
</div>
</body>
<script>
    $(".nav li").on("click", function() {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });
</script>
</html>
