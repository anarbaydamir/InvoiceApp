<%--
  Created by IntelliJ IDEA.
  User: anarbaydamirov
  Date: 5/28/20
  Time: 11:47 AM
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-3.3.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
    <%--    datatable--%>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <%--    --------------%>
    <%--    Fontawesome--%>
    <script src="https://kit.fontawesome.com/32ebf16f88.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="assets/js/invoice.js"></script>
    <title>Digital Invoice</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <br>
    <input type="hidden" id="message" value="${message}">
    <h4>Invoice</h4>
    <button type="button" onclick="insertModal()" class="btn btn-primary" data-toggle="modal" data-target="#infoModal">
        + Add Invoice
    </button>
    <hr/>
</div>
<div class="container">
    <table id="dtInvoice"  class="table table-striped table-bordered" cellspacing="0" width="100%">
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
            <th></th>
            <th></th>
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
                            <button type="submit" onclick="sendIdForPay(${in.id})" class="btn btn-danger" data-toggle="modal" data-target="#payModal"
                            >Pending</button>
                        </c:when>
                        <c:when test="${in.paymentStatus==1}">
                            <button type="submit" class="btn btn-success" disabled>Paid</button>
                        </c:when>
                    </c:choose>
                       </td>
                <td style="width: 2%">
                    <button type="submit" onclick="setInfoToModal(${in.id},'${in.invoiceNumber}','${in.invoiceDate}','${in.clientId.id}','${in.vendorId.id}',
                                                                    '${in.netAmount}','${in.projectId.id}','${in.taxAmount}','${in.note}','${in.paymentStatus}')"
                            class="btn btn-secondary" data-toggle="modal" data-target="#infoModal">
                        <i class="fas fa-pen"></i>
                    </button>
                </td>
                <td style="width: 2%">
                    <button type="submit" onclick="setIdForDelete(${in.id})" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--modal--%>
<div id="infoModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Invoice</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="invoice" method="post" onsubmit="return validationInvoice()">
            <div class="modal-body" id="testModal">
                <input type="hidden" name="id" id="clientId">
                <input type="hidden" name="action" id="actionType">
                <input type="hidden" name="paymentStatus" id="paymentStatus">
                <div class="row">
                        <div class="col-md-6">
                            <label for="invoiceNumber_txt">Invoice Number</label>
                            <input id="invoiceNumber_txt" name="invoiceNumber" type="text" class="form-control" placeholder="Invoice Number" required>
                        </div>
                         <div class="col-md-6">
                             <label for="invoiceDate_txt">Invoice Date</label>
                             <input type="date" id="invoiceDate_txt" name="invoiceDate" class="form-control" required>
                        </div>
                        <div class="col-sm-6">
                            <label for="clientName">Client Name</label>
                            <select name="clientName" id="clientName" class="form-control">
                                <option value="-1">Choose</option>
                                <c:forEach items="${clients}" var="cl">
                                <option value="${cl.id}">${cl.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <label for="vendorName">Vendor Name</label>
                            <select name="vendorName" id="vendorName" class="form-control">
                                <option value="-1">Choose</option>
                                <c:forEach items="${vendors}" var="ve">
                                    <option value="${ve.id}">${ve.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-12">
                            <label for="netAmount_txt">Net Amount</label>
                            <input type="text" id="netAmount_txt" name="netAmount" class="form-control" placeholder="Net Amount" required>
                        </div>
                        <div class="col-sm-6">
                            <label for="projectName">Project Name</label>
                            <select name="projectName" id="projectName" class="form-control">
                                <option value="-1">Choose</option>
                                <c:forEach items="${projects}" var="pr">
                                    <option value="${pr.id}">${pr.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <label for="taxPerCent">Tax Per cent</label>
                            <select name="taxPerCent" id="taxPerCent" class="form-control">
                                <option value="18">18</option>
                                <option value="0">0</option>
                            </select>
                        </div>
                        <div class="col-sm-12">
                            <label for="note_txt">Note</label>
                            <input type="text" id="note_txt" name="note" class="form-control" placeholder="Note">
                        </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>
<%--------------%>
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Invoice</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete?</p>
            </div>
            <div class="modal-footer">
                <form action="invoice/delete" method="post">
                    <input type="hidden" name="id" id="idForDelete" value="">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%-----------------%>
<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Invoice</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you change status of the invoice?</p>
            </div>
            <div class="modal-footer">
                <form action="invoice/pay" method="post">
                    <input type="hidden" name="id" id="idForPay" value="">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
