$(document).ready(function () {
    $('#dtProject').DataTable();
    $('.dataTables_length').addClass('bs-select');
});

function setInfoToModal(pId,name){
    let projectName = document.getElementById("projectName_txt")
    let id = document.getElementById("projectId");
    let action = document.getElementById("actionType")
    projectName.value=name;
    id.value=pId;
    action.value="update";
}
function setIdForDelete(id) {
    let deleteId = document.getElementById("idForDelete");
    deleteId.value=id;
}
function insertModal() {
    let projectName = document.getElementById("projectName_txt")
    let id = document.getElementById("projectId");
    let action = document.getElementById("actionType")
    projectName.value="";
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
