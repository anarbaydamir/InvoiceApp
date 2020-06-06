$(document).ready(function () {
    $('#dtInvoice').DataTable();
    $('.dataTables_length').addClass('bs-select');
});
window.onload = function () {
    let urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("fromDate"))
        document.getElementById("fromDate_id").value = urlParams.get("fromDate");
    if (urlParams.has("toDate"))
        document.getElementById("toDate_id").value = urlParams.get("toDate");
}
