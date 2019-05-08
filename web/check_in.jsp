
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>TEST</title>

    <script type="text/javascript">
        var ws = null;
        function connect()
        {
            var msg = document.getElementById("msg").value;
            var dialog_id = document.getElementById("dialog_id").value;
            if (ws != null)
            {
                var pQuery = {
                    code: 10,
                    from: -1,
                    dialog_id: dialog_id,
                    data_type: 0,
                    data: msg
                };

                ws.send(JSON.stringify(pQuery));
                console.log("имитационный запрос выполнен");
                return;
            }


            ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tdbbroker");

            ws.onopen = function (){
                console.log("opened");
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
</body>
</html>