$(document).ready(function(){

function wrongLoginOrPassword(){
    var login = $("#login");
    var password = $("#password");
    login.css({"backgroundColor": "#57273d"});
    password.css("backgroundColor", "#57273d");
    setTimeout(function(){
        var login = $("#login");
        var password = $("#password");
        login.css({"background": "none"});
        password.css("background", "none");
    }, 1000);
}

function dbErr() {
    console.log("Ошибка в БД");
}

function check(){
    var login = $("#login").val();
    var password = $("#password").val();
    if (login.length < Consts.MIN_LOGIN_LENGTH || password.length < Consts.MIN_PASSWORD_LENGTH) return false;
    return true;
}

function submit(){
    if (!check()) return;
    var login = $("#login").val();
    var password = $("#password").val();
    $.ajax({
        url: '/Messenger_war_exploded/aut',
        type: 'POST',
        data: 'login='+login+'&password='+password,
        success: function (success) {
            console.log(success);
            if (success == 0){
                wrongLoginOrPassword();
                return;
            }
            if (success == -1){
                dbErr();
                return;
            }
            if (success == 1){
                location.href = "/Messenger_war_exploded/messenger";
                return;
            }
        }
    });
}

$("#submit").click(function(){
    submit();
    });
});
