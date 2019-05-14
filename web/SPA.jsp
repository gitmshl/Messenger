<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>My Dialogs</title>
    <link rel="stylesheet" href="css/SPA/my_dialogs.css">
    <link rel="stylesheet" href="fonts/SPA/fonts.css">
    <link rel="stylesheet" href="fonts/SPA/rubik/rubik.css">
    <link rel="stylesheet" href="fonts/SPA/roboto_vk_font/roboto_vk_font.css">

    <script type="text/javascript" src="js/Sender/Sender.js"></script>
    <script type="text/javascript" src="js/SST/SST.js"></script>
    <script type="text/javascript" src="js/Consts/Consts.js"></script>
    <script type="text/javascript" src="js/UC/UC.js"></script>
    <script type="text/javascript" src="js/Timer/Timer.js"></script>
    <script type="text/javascript" src="js/ProtocolHandler/PH.js"></script>
    <script type="text/javascript" src="js/Painter/Painter.js"></script>


    <script type="text/javascript">
        window.onload = function(){
            SST.setUser(8, "Musa", "halilovmusa", "halilovmusa@gmail.com");
            var ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tph");

            Sender.setWs(ws);

            ws.onopen = function (){
                UC.start();
            }

            ws.onmessage = function(data){
                PH.handl(data.data);
            }

            ws.onclose = function() {
                console.log("closed");
            }

            ws.onerror = function(){
                console.log("error of connection");
            }
        }
    </script>

</head>
<body style="display: none;">
<header>
    <div class="header">
        <div class="subheader">
            <div class="logo"></div>
            <div class="search_block">
                <form action="">
                    <div class="search_field">
                        <input type="text" id="search_input" name="Global_Search_Line" class="placeholder_style" placeholder="Search...">
                        <input type="image" id="search_button" name = "Global_Search_Button" src="images/SPA/search_button.png" alt="Search">
                    </div>
                </form>
            </div>
            <div class="right_column">
                <div class="signOut_block">
                    <form action="">
                        <div class="signOut_a">
                            <a class="right_top" href="">Sign out</a>
                        </div>
                    </form>
                </div>
                <div class="options_block">
                    <form action="">
                        <div class="options_a">
                            <a class="right_top" href="">Options</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="clear"></div>
<div class="wrapper">
    <div class="leftSidebar">
        <ul class="left_sidebar_ul">
            <li>
                <div class="my_page">
                    <a href="">
                        My page
                    </a>
                </div>
            </li>
            <li>
                <div class="my_friends">
                    <a href="..\Page\Friends\MFS.php">
                        My friends
                    </a>
                </div>
            </li>


            <li>
                <div class="my_messages">
                    <a href="DS.php">
                        My messages
                    </a>
                </div>
            </li>
            <li>
                <div class="my_groups">
                    <a href="">
                        My groups
                    </a>
                </div>
            </li>
        </ul>
    </div>

    <div id="DialogList" style="display: none;">
        <div class="middle_content">
            <form action="">
                <div class="search_field_large">
                    <input type="text" id="search_input_large" name="DS_Line" class="placeholder_style" placeholder="Search...">
                    <input type="image" id="search_button_large" src="images/SPA/search_button.png" alt="Search">
                </div>
            </form>

            <div id="Dialogs">
                <%--<div class="dialog_block">--%>
                    <%--<div class="avatar_block">--%>
                        <%--<img src="img/mfB4lkFrPe0.jpg">--%>
                    <%--</div>--%>

                    <%--<a href="google.com" class="profile_name_link" onclick="alert('hi'); return false;">--%>
                        <%--<div class="profile_name">--%>
                            <%--<div class="sub1">--%>
                                <%--Musa Halilov--%>
                            <%--</div>--%>
                            <%--<div class="sub2 dread">--%>
                                <%--some textsome textsome--%>
                            <%--</div>--%>
                            <%--<div class="sub3" style="display: none;">--%>
                            <%--</div>--%>
                            <%--<div class="date">--%>
                                <%--9:05--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</a>--%>
                <%--</div>--%>
            </div>

        </div>
    </div>

    <div id="Dialog" style="display: none;">
        <div id="Messages">
            Dialog
        </div>
    </div>

</div>
</body>
</html>
