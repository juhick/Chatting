function validateForm() {
    var username = document.forms["sign_in"]["username"].value;
    var password = document.forms["sign_in"]["password"].value;
    var password_two = document.forms["sign_in"]["password_two"].value;

    if (username == null || username === ""){
        alert("用户名必须填写");
        return false;
    }
    if (password == null || password === ""){
        alert("密码必须填写");
        return false;
    }
    if (password_two == null || password_two === ""){
        alert("确认密码必须填写");
        return false;
    }

    if (password_two !== password){
        alert("两次输入的密码不一致");
        return false;
    }

}