<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TestConnector</title>

    <script type="text/javascript">
      var ws = null;

      function connect(){
        if (ws != null)
        {
          return;
        }

        ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tsender");

        ws.onopen = function (){
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
