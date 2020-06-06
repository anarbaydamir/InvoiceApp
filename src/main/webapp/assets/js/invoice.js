$(document).ready(function () {
    $('#dtInvoice').DataTable();
    $('.dataTables_length').addClass('bs-select');
});
window.onload = function () {
    let operationMessage = document.getElementById("message");
    if (operationMessage.value.toString() == "success"){
        toastr.success('Successfully operated');
    }
    else if (operationMessage.value.toString() == "error"){
        toastr.error('Error occured');
    }
}
function setInfoToModal(inId,inNumberStr,inDateStr,clientNameId,vendorNameId,netAmountStr,
                        projectNameId,taxAmountStr,noteStr,paymentStatusStr) {
    let id = document.getElementById("clientId");
    id.value=inId;
    let action = document.getElementById("actionType");
    action.value="update";
    let invoiceNumber = document.getElementById("invoiceNumber_txt");
    invoiceNumber.value=inNumberStr;
    let invoiceDate = document.getElementById("invoiceDate_txt");
    invoiceDate.value=inDateStr;
    let clientName = document.getElementById("clientName");
    if (clientNameId == '')
        clientName.value = -1;
    else
        clientName.value = clientNameId;
    let vendorName = document.getElementById("vendorName");
    if (vendorNameId == '')
        vendorName.value= -1;
    else
        vendorName.value=vendorNameId;
    let netAmount = document.getElementById("netAmount_txt");
    netAmount.value = parseFloat(netAmountStr);
    let projectName = document.getElementById("projectName");
    if (projectNameId == '')
        projectName.value= -1;
    else
        projectName.value=projectNameId;

    let taxPerCent = document.getElementById("taxPerCent");
    if (taxAmountStr == 0)
        taxPerCent.value=0
    else
        taxPerCent.value=18;
    let note = document.getElementById("note_txt");
    note.value=noteStr;

    let paymentStatus = document.getElementById("paymentStatus");
    paymentStatus.value=paymentStatusStr;
}
function insertModal() {
    document.getElementById("clientId").value=null;
    document.getElementById("actionType").value="insert";
    document.getElementById("invoiceNumber_txt").value="";
    document.getElementById("invoiceDate_txt").value="";
    document.getElementById("clientName").value=-1;
    document.getElementById("vendorName").value=-1;
    document.getElementById("netAmount_txt").value="";
    document.getElementById("projectName").value=-1;
    document.getElementById("taxPerCent").value=18;
    document.getElementById("note_txt").value="";
    document.getElementById("paymentStatus").value=0;
}
function setIdForDelete(id) {
    let deleteId = document.getElementById("idForDelete");
    deleteId.value=id;
}
function sendIdForPay(id) {
    let payId = document.getElementById("idForPay");
    payId.value=id;
}
function validationInvoice() {
    let clientName = document.getElementById("clientName");
    let vendorName = document.getElementById("vendorName");
    if (clientName.value=="-1" && vendorName.value == "-1"){
        toastr.error("please fill Client Name or Vendor Name")
        return false;
    }
    if (clientName.value!="-1" && vendorName.value != "-1"){
        toastr.error("please choose on of the Client Name or Vendor Name")
        return false;
    }
    let projectName = document.getElementById("projectName");
    if (projectName.value == "-1"){
        toastr.error("please choose Project");
        return false;
    }
    let netAmount = document.getElementById("netAmount_txt");
    if (isNaN(netAmount.value)){
        toastr.error("please fill proper Net Amount");
        return false;
    }
}
