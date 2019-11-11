function validateForm() {
    var username = document.forms["sign_up"]["username"].value;
    var password = document.forms["sign_up"]["password"].value;

    if (username == null || username === ""){
        alert("用户名必须填写");
        return false;
    }
    if (password == null || password === "") {
        alert("密码必须填写");
        return false;
    }
}