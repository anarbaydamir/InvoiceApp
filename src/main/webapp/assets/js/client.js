$(document).ready(function () {
    $('#dtClient').DataTable();
    $('.dataTables_length').addClass('bs-select');
});

function setInfoToModal(cId,name){
    let clientName = document.getElementById("clientName_txt")
    let id = document.getElementById("clientId");
    let action = document.getElementById("actionType")
    clientName.value=name;
    id.value=cId;
    action.value="update";
}
function setIdForDelete(id) {
    let deleteId = document.getElementById("idForDelete");
    deleteId.value=id;
}
function insertModal() {
    let clientName = document.getElementById("clientName_txt")
    let id = document.getElementById("clientId");
    let action = document.getElementById("actionType")
    clientName.value="";
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
