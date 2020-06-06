<%--
  Created by IntelliJ IDEA.
  User: anarbaydamirov
  Date: 5/29/20
  Time: 10:39 AM
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
    <script src="assets/js/reportbydate.js"></script>
    <title>Digital Invoice</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <br>
    <h4>Report</h4>
    <form action="datereport" method="get">
    <div class="row">
        <div class="col-sm-4">
            <input type="date" class="form-control" id="fromDate_id" name="fromDate" required/>
        </div>
        <div class="col-sm-4">
            <input type="date" class="form-control" id="toDate_id" name="toDate" required />
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-info btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#infoModal">
                Search
            </button>
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
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="clientAmount_id">Client Amount</label>
                     <input type="text" id="clientAmount_id" class="form-control" value="${clientAmount.totalAmount}" disabled>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="vendorAmount_id">Vendor Amount</label>
                    <input type="text" id="vendorAmount_id" class="form-control" value="${vendorAmount.totalAmount}" disabled>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="profitLost_id">Profit/Lost</label>
                    <input type="text" id="profitLost_id" class="form-control" value="${clientAmount.totalAmount - vendorAmount.totalAmount}" disabled>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="netAmount_id">Net Amount Profit/Lost</label>
                    <input type="text" id="netAmount_id" class="form-control" value="${clientAmount.netAmount - vendorAmount.netAmount}" disabled>
                </div>
            </div>
        </div>
        <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="clientTax_id">Client TAX</label>
                        <input type="text" id="clientTax_id" class="form-control" value="${clientAmount.taxAmount}" disabled>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="vendorTax_id">Vendor TAX</label>
                        <input type="text" id="vendorTax_id" class="form-control" value="${vendorAmount.taxAmount}" disabled>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="taxProfit_id">TAX Profit/Lost</label>
                        <input type="text" id="taxProfit_id" class="form-control" value="${clientAmount.taxAmount - vendorAmount.taxAmount} " disabled>
                    </div>
                </div>
        </div>
        <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="expectedClientAmount_id">Expected Client Amount</label>
                        <input type="text" id="expectedClientAmount_id" class="form-control" value="${expectedClientAmount.totalAmount}" disabled>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="expectedVendorAmount_id">Expected Vendor Amount</label>
                        <input type="text" id="expectedVendorAmount_id" class="form-control" value="${expectedVendorAmount.totalAmount}" disabled>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="expectedProfitLost_id">Expected Profit/Lost</label>
                        <input type="text" id="expectedProfitLost_id" class="form-control" value="${expectedClientAmount.totalAmount - expectedVendorAmount.totalAmount}" disabled>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="expectedNetAmount_id">Expected Net Amount Profit/Lost</label>
                        <input type="text" id="expectedNetAmount_id" class="form-control" value="${expectedClientAmount.netAmount - expectedVendorAmount.netAmount}" disabled>
                    </div>
                </div>
        </div>
</div>
</body>
</html>
