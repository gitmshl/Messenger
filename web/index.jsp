<%@ page import="com.mshl.Protocol_Handler.Protocol_Handler" %>
<%@ page import="com.mshl.DB_Broker.DB_Broker_Test" %>
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
              code: 10,
              from: user_id,
              dialog_id: 6,
              data_type: 0,
              data: "Тестовый запрос"
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
          console.log(d);
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
