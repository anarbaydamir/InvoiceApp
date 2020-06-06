$(document).ready(function () {
    $('#dtInvoice').DataTable();
    $('.dataTables_length').addClass('bs-select');
});
window.onload = function () {
    let urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("clientName"))
        document.getElementById("clientName_id").value=urlParams.get("clientName");
    if (urlParams.has("vendorName"))
        document.getElementById("vendorName_id").value=urlParams.get("vendorName");
    if (urlParams.has("projectName"))
        document.getElementById("projectName_id").value=urlParams.get("projectName");
    if (urlParams.has("paid"))
        document.getElementById("paid_id").value=urlParams.get("paid");
    if (urlParams.has("fromDate"))
        document.getElementById("fromDate_id").value = urlParams.get("fromDate");
    if (urlParams.has("toDate"))
        document.getElementById("toDate_id").value = urlParams.get("toDate");
}
function checkFilters() {
    let client = document.getElementById("clientName_id");
    let vendor = document.getElementById("vendorName_id");
    if (client.value != "-1" && vendor.value != "-1"){
        toastr.error("please choose one of Client and Vendor");
        return false;
    }

    let fromDate = document.getElementById("fromDate_id");
    let toDate = document.getElementById("toDate_id");
    if (fromDate.value != "" || toDate.value !="")
    {
        if (fromDate.value == "" || toDate.value == ""){
            toastr.error("please choose both date");
            return false;
        }
    }
}
function resetForm() {
    document.getElementById('reportFrom').reset();
}
$('.select2').select2();
