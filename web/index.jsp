<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TestConnector</title>

    <script type="text/javascript">
      var ws = null;

      function connect(){
        if (ws != null)
        {
          ws.send("df");
          return;
        }
          ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tsc  ");
        ws.onopen = function (){
          console.log("opened");
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
