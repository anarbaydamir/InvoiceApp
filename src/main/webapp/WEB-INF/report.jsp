<%--
  Created by IntelliJ IDEA.
  User: anarbaydamirov
  Date: 5/29/20
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script src="assets/js/report.js"></script>
    <%--    search dropdown--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    <title>Digital Invoice</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <br>
    <h4>Report</h4>
    <form action="report" method="get" id="reportFrom" onsubmit="return checkFilters()">
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="clientName_id">Client</label>
                    <select id="clientName_id" class="form-control select2" name="clientName">
                        <option value="-1">Choose</option>
                        <c:forEach items="${clients}" var="cl">
                            <option value="${cl.id}">${cl.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="vendorName_id">Vendor</label>
                    <select id="vendorName_id" class="form-control select2" name="vendorName">
                        <option value="-1">Choose</option>
                        <c:forEach items="${vendors}" var="ve">
                            <option value="${ve.id}">${ve.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="projectName_id">Project</label>
                    <select id="projectName_id" class="form-control select2" name="projectName">
                        <option value="-1">Choose</option>
                        <c:forEach items="${projects}" var="pr">
                            <option value="${pr.id}">${pr.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="paid_id">Payment Status</label>
                    <select id="paid_id" class="form-control" name="paid">
                        <option value="-1">Choose</option>
                        <option value="0">Pending</option>
                        <option value="1">Paid</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                <label for="fromDate_id">From Date</label>
                <input type="date" class="form-control" id="fromDate_id" name="fromDate"/>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="toDate_id">To Date</label>
                    <input type="date" class="form-control" id="toDate_id" name="toDate"/>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="float-right">
                <div class="form-group">
                <button type="button" class="btn btn-primary" onclick="resetForm()">
                    <i class="fas fa-sync-alt"></i>
                </button>
                <button type="submit" class="btn btn-info btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#infoModal">
                    Search
                </button>
                </div>
                </div>
            </div>
        </div>
    </form>
    <hr/>
</div>
<div class="container">
    <table id="dtInvoice" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>#</th>
            <th>Invoice Number</th>
            <th>Invoice Date</th>
            <th>Client</th>
            <th>Vendor</th>
            <th>Net Amount</th>
            <th>Tax Amount</th>
            <th>Total Amount</th>
            <th>Project</th>
            <th>Note</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${invoices}" var="in">
            <tr>
                <td style="width: 2%">${in.id}</td>
                <td>${in.invoiceNumber}</td>
                <td>${in.invoiceDate}</td>
                <td>${in.clientId.name}</td>
                <td>${in.vendorId.name}</td>
                <td>${in.netAmount}</td>
                <td>${in.taxAmount}</td>
                <td>${in.totalAmount}</td>
                <td>${in.projectId.name}</td>
                <td>${in.note}</td>
                <td>
                    <c:choose>
                        <c:when test="${in.paymentStatus==0}">
                            <button type="submit" class="btn btn-danger" disabled>Pending</button>
                        </c:when>
                        <c:when test="${in.paymentStatus==1}">
                            <button type="submit" class="btn btn-success" disabled>Paid</button>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="netAmount_id">Net Amount</label>
                <input type="text" id="netAmount_id" class="form-control" value="${allAmounts.netAmount}" readonly>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="taxAmount_id">Tax Amount</label>
                <input type="text" id="taxAmount_id" class="form-control" value="${allAmounts.taxAmount}" readonly>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="totalAmount_id">Total Amount</label>
                <input type="text" id="totalAmount_id" class="form-control" value="${allAmounts.totalAmount}" readonly>
            </div>
        </div>
    </div>
</div>
</body>
<style>
    .select2-container .select2-selection--single{
        height:34px !important;
    }
    .select2-container--default .select2-selection--single{
        border: 1px solid #ccc !important;
        border-radius: 0px !important;
    }
</style>
<script>
    $('.select2').select2();
</script>
</html>
