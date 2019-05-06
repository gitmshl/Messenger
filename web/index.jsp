<%@ page import="com.mshl.Protocol_Handler.Protocol_Handler" %>
<%@ page import="com.mshl.Protocol_Handler.PH_Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TestConnector</title>

    <script type="text/javascript">
      var ws = null;

      var user_id = 0;

      function connect(){
        if (ws != null)
        {
          var pQuery = {
              code: 0,
              from: user_id,
              dialog_id: 4,
              data_type: 0,
              data: "Halilov Musa"
          };
          ws.send(JSON.stringify(pQuery));
          console.log(pQuery);
          console.log("имитационный запрос отправлен");
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
          console.log("data: " + d["data"]);
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
  <body>
      <button onclick="connect()">connect</button>
  </body>



</html>
