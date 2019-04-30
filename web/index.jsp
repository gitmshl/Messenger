<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TestConnector</title>

    <script type="text/javascript">
      var ws = null;

      function connect(){
        if (ws != null)
        {
          var pquery = {
              code: 30,
              from: 7,
              dialog_id: -1,
              data_type: 0,
              data: ""
          };

          ws.send(JSON.stringify(pquery));
          console.log("имитационный запрос отправлен")
          return;
        }
          ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tmessenger?id=7");
        ws.onopen = function (){
          console.log("opened");
        }

        ws.onmessage = function(data){
          var d = JSON.parse(data.data);
          console.log(d["code"]);
          console.log(d["from"]);
          console.log(d["dialog_id"]);
          console.log(d["data_type"]);
          console.log(d["data"]);
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
