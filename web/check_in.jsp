
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="css/CheckIn/check_in_style.css">
    <link rel="stylesheet" href="fonts/CheckIn/fonts.css">
    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript">

        var err = false;

        function checkName() {
            var name = $("#Name")[0];
            if (name.value.length < 2){
                name.style.border = "1px solid red";
                err = true;
            }
            else {
                name.style.border = "none";
                err = false;
            }
        }

        function checkEmail() {
            var email = $("#Email")[0];
            if (email.value.length < 5) {
                email.style.border = "1px solid red";
                err = true;
            }
            else{
                email.style.border = "none";
                err = false;
            }
        }

        function checkLogin(){
            var login = $("#Login")[0];
            if (login.value.length < 5) {
                login.style.border = "1px solid red";
                err = true;
            }
            else {
                login.style.border = "none";
                err = false;
            }
        }

        function checkPassword(){
            var pass = $("#Password")[0];
            if (pass.value.length < 8) {
                pass.style.border = "1px solid red";
                err = true;
            }
            else {
                pass.style.border = "none";
                err = false;
            }
        }

        function checkConfirmPassword(){
            var confPass = $("#Repeat_Password")[0];
            var pass = $("#Password")[0].value;
            if (confPass.value !== pass){
                confPass.style.border = "1px solid red";
                err = true;
            }
            else {
                confPass.style.border = "none";
                err = false;
            }
        }

        $(document).ready(function(){
            $("#Email").change(function(){
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: 'type=email&email=' + $("#Email")[0].value,
                    success: function(data){
                        if (data === "wrong"){
                            err = true;
                            $("#Email")[0].style.border = "1px solid red";
                        }
                        else if (data === "ok") err = false;
                    }
                });
            });
            $("#Login").change(function(){
                $.ajax({
                    url: '',
                    type: 'POST',
                    data: 'type=login&login=' + $("#Login")[0].value,
                    success: function(data){
                        if (data === "wrong"){
                            err = true;
                            $("#Login")[0].style.border = "1px solid red";
                        }
                        else if (data === "ok") err = false;
                    }
                });
            });
            $("#submit").click(function(){
                var name = $("#Name")[0].value,
                    email = $("#Email")[0].value,
                    login = $("#Login")[0].value,
                    password = $("#Password")[0].value,
                    confPass = $("#Repeat_Password")[0].value;

                $.ajax({
                    url:'',
                    type: 'POST',
                    data: 'type=submit&name=' + name + '&login='
                        + login + '&email=' + email + '&password=' + password
                        + '&confirmPassword=' + confPass,
                    success: function(data){
                        if (data === "ok")
                            location.href = "";
                        else if (data === "wrong") err = true;
                    }
                });
            });
        });
    </script>

</head>
<body>
<header>
    <div class="header">
        <form action="">
            <input  type="image" id="log_in" name="log_in" src="images/log_in.png" alt="Войти"></input>
        </form>
    </div>
</header>
<div class="wrapper_big">
    <div class="wrapper">
        <div class="box_bkg">
            <div class="icon"></div>
            <div class="register_now"></div>
            <form action="" method = "POST">
                <div class="field" id="first_field">
                    <span class="username_set_icon"><input type="text" name="Name" id="Name" class="color-text" placeholder="Name" maxlength="30" oninput="checkName()"></span>
                </div>
                <div class="field">
                    <span class="username_set_icon"><input type="text" name="Email" id="Email" class="color-text" placeholder="Email" maxlength="30" oninput="checkEmail()"></span>
                </div>
                <div class="field">
                    <span class="username_set_icon"><input type="text"  name="Login" id="Login" class="color-text" placeholder="Login" maxlength="30" oninput="checkLogin()"></span>
                </div>
                <div class="field" >
                    <span class="password_set_icon"><input type="password" name="Password" id="Password" class="color-text" class="password_set_icon_try" oninput="checkPassword()" placeholder="Password" maxlength="60"></span>
                </div>
                <div class="field" >
                    <span class="password_set_icon"><input type="password" name="Repeat_Password" id="Repeat_Password" class="color-text" oninput="checkConfirmPassword()" placeholder="Confirm password" maxlength="60"></span>
                </div>
                <input  type="image" id="submit" name="Authorization_button" src="images/Check_in.png" value = "Зарегистрироваться" onclick="Submit"></input>
            </form>
        </div>
    </div>
</div>
</body>
</html>