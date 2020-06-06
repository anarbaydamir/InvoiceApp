$(document).ready(function () {
    $('#dtVendor').DataTable();
    $('.dataTables_length').addClass('bs-select');
});

function setInfoToModal(cId,name){
    let vendorName = document.getElementById("vendorName_txt")
    let id = document.getElementById("vendorId");
    let action = document.getElementById("actionType")
    vendorName.value=name;
    id.value=cId;
    action.value="update";
}
function setIdForDelete(id) {
    let deleteId = document.getElementById("idForDelete");
    deleteId.value=id;
}
function insertModal() {
    let vendorName = document.getElementById("vendorName_txt")
    let id = document.getElementById("vendorId");
    let action = document.getElementById("actionType")
    vendorName.value="";
    id.value="";
    action.value="insert";
}
window.onload = function () {
        let operationMessage = document.getElementById("message");
        if (operationMessage.value.toString() == "success"){
            toastr.success('Successfully operated');
        }
        else if (operationMessage.value.toString() == "error"){
            toastr.error('Error occured');
        }
    }
