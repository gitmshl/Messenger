<%@ page import="com.mshl.Protocol_Handler.Protocol_Handler" %>
<%@ page import="com.mshl.DB_Broker.DB_Broker_Test" %>
<%@ page import="com.mshl.Protocol_Handler.PH_Test" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TestConnector</title>

      <script type="text/javascript" src="js/Sender/Sender.js"></script>
        <script type="text/javascript" src="js/SST/SST.js"></script>
      <script type="text/javascript" src="js/Consts/Consts.js"></script>
      <script type="text/javascript" src="js/UC/UC.js"></script>
      <script type="text/javascript" src="js/Timer/Timer.js"></script>
      <script type="text/javascript" src="js/ProtocolHandler/PH.js"></script>

    <script type="text/javascript">

        var ws = null;

        SST.setUser(0, "Musa", "halilovmusa", "halilovmusa@gmail.com");

      function connect(){

        if (ws != null) return;

        ws = new WebSocket("ws://localhost:8080/Messenger_war_exploded/tph");

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
  <body>
      <button onclick="connect()">connect</button>
  </body>



</html>
