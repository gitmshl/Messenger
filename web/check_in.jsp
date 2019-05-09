<%@ page import="com.mshl.Protocol_Handler.PH_Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>TEST</title>

    <script type="text/javascript">
        var ws = null;
        var user_id = 0;

        function refresh()
        {
            user_id = <%= PH_Test.id %>
            console.log("id: " + <%= PH_Test.id %>);
        }

        function inc()
        {
            user_id++;
        }

        function dec()
        {
            user_id--;
        }

        function show()
        {
            document.getElementById("show").innerText="user_id = " + user_id;
        }

        function connect()
        {
            var msg = document.getElementById("msg").value;
            var dialog_id = document.getElementById("dialog_id").value;
            if (ws != null)
            {
                var pQuery = {
                    code: 1,
                    from: user_id,
                    dialog_id: dialog_id,
                    data_type: 0,
                    data: ""
                };

                ws.send(JSON.stringify(pQuery));
                console.log("имитационный запрос выполнен");
                return;
            }


            ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tph");

            ws.onopen = function (){
                user_id = <%= PH_Test.id %>
                console.log("opened");
            }

            ws.onmessage = function(data){
                var d = JSON.parse(data.data);
                console.log("code: " + d["code"]);
                console.log("from: " + d["from"]);
                console.log("dialog_id: " + d["dialog_id"]);
                console.log("data_type: " + d["data_type"]);
                if (d["code"] == 10) {
                    var d1 = JSON.parse(d["data"]);
                    console.log("data: ");
                    console.log("   name: " + d1["name"]);
                    console.log("   avatar: " + d1["avatar"]);
                    console.log("   msg: " + d1["msg"]);
                }
                else{
                    console.log("data: " + d["data"]);
                }
                console.log("");
            }

            ws.onclose = function(){
                console.log("closed");
            }
        }
    </script>

</head>
<body>
    <select id="dialog_id">
        <option value="6">6 (8 & 9 users)</option>
        <option value="7">7 (7 & 8 users)</option>
    </select>
    <input type="text" id="msg" placeholder="msg...">
    <button onclick="connect()">send msg</button>
    <button onclick="refresh()">refresh id</button>
    <p>
        <button onclick="inc()">+1</button>
        <button onclick="dec()">-1</button>
        <button onclick="show()">show id</button>
    </p>
    <p id="show"></p>

</body>
</html>