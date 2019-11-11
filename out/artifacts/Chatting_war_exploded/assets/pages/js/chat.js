function validData() {
    var message = document.forms["ms"]["input_textarea"].value;

    if(message == null || message === ""){
        alert("信息不能为空！");
        return false;
    }
}