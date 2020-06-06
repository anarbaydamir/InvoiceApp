$(document).ready(function () {
    $('#dtInvoice').DataTable();
    $('.dataTables_length').addClass('bs-select');
});
function checkProject() {
    let projectName = document.getElementById("projectName_id");
    if (projectName.value == "-1"){
        toastr.error("please choose Project");
        return false;
    }
}
window.onload = function () {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("projectId"))
        document.getElementById("projectName_id").value = urlParams.get("projectId");
}
