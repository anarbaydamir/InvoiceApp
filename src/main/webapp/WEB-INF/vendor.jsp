<%--
  Created by IntelliJ IDEA.
  User: anarbaydamirov
  Date: 5/27/20
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <%--    toastr--%>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
<%--    datatable--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<%--    --------------%>
<%--    Fontawesome--%>
    <script src="https://kit.fontawesome.com/32ebf16f88.js" crossorigin="anonymous"></script>
    <script src="assets/js/vendor.js"></script>
    <title>Digital Invoice</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <input type="hidden" id="message" value="${message}">
    <br>
    <h4>Vendor</h4>
    <button type="button" onclick="insertModal()" class="btn btn-primary" data-toggle="modal" data-target="#infoModal">
        + Add Vendor
    </button>
    <hr/>
</div>
<div class="container">
    <table id="dtVendor"  class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vendors}" var="ve">
        <tr>
            <td style="width: 2%">${ve.id}</td>
            <td>${ve.name}</td>
            <td style="width: 2%">
                <button type="submit" onclick="setInfoToModal(${ve.id},'${ve.name}')" class="btn btn-secondary" data-toggle="modal" data-target="#infoModal">
                <i class="fas fa-pen"></i>
                </button>
            </td>
            <td style="width: 2%">
               <button type="submit" onclick="setIdForDelete(${ve.id})" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
                   <i class="fas fa-trash"></i>
               </button>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Modal -->
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Vendor</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="vendor" method="post">
            <div class="modal-body">
                    <input type="hidden" name="id" id="vendorId">
                    <input type="hidden" name="action" id="actionType">
                    <input id="vendorName_txt" name="name" type="text" class="form-control" placeholder="Vendor name" required>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>
<%---------%>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Vendor</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete?</p>
            </div>
            <div class="modal-footer">
                <form action="vendor/delete" method="post">
                <input type="hidden" name="id" id="idForDelete" value="">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
