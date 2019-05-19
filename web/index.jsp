<%@ page import="com.mshl.HASH_STORE.HST" %>
<%@ page contentType="text/html;charset=UTF8" language="java" %>

<%
  Cookie[] cookies = request.getCookies();
  int id = -1;
  String uid = request.getSession().getId();
  if (cookies != null)
    for (Cookie cookie: cookies)
      if (cookie.getName().equals("ID"))
      {
        try
        {
          id = Integer.parseInt(cookie.getValue());
        }
        catch (NumberFormatException e)
        {
          id = -1;
          break;
        }
      }
    if (id != -1 && HST.exist(uid, id))
    {
%>
  <script type="text/javascript">
    location.href = "/Messenger_war_exploded/messenger";
  </script>
<%
    }
    else
    {
%>

<!doctype html>

<html lang="ru-RU">
<head>
  <meta charset="UTF-8">
  <title>Вход</title>
  <link rel="stylesheet" href="css/LogIn/check_in_style.css">
  <link rel="stylesheet" href="fonts/LogIn/fonts.css">
  <script type="text/javascript" src="js/Consts/Consts.js"></script>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/LogIn.js"></script>

</head>
<body id="log_in_body">
<header>
  <div class="header">
    <form action="">
      <input  type="image" id="log_in" name="check-in" src="images/LogIn/Check_in_small.png" alt="Зарегистрироваться"></input>
    </form>
  </div>
</header>
<div class="wrapper_big">
  <div class="wrapper_log_in">
    <div class="box_bkg_log_in">
      <div class="log_in_icon"></div>
      <div class="log_in_text"></div>
      <div class="field" id="first_field">
        <span class="username_set_icon"><input type="text" id="login" name="LogIn"  class="color-text" placeholder="Login" maxlength="18"></span>
      </div>
      <div class="field" >
        <span class="password_set_icon"><input type="password" id="password" name="Password" class="color-text" class="password_set_icon_try" placeholder="Password" maxlength="30"></span>
      </div>
      <input  type="image" id="submit" name="Log-in_button" src="images/LogIn/log_in_large.png" value = "Войти"></input>
    </div>
  </div>
</div>
</body>
</html>

<%
  }
%>
<!-- #57273d - крутой цвет для посдветки ошибок -->