<%@ page import="logic.*" %>
<%@ page import="websocket.GameServerEndPoint" %>
<%@ page import="java.awt.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Game</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="chat.js"></script>

    <script>
        var jsonObj;
        window.addEventListener("click", checkMouseClick);

        function checkMouseClick(mouse) {
            jsonObj = mouse.clientX + " , " + mouse.clientY;
            chatClient.send(JSON.stringify(jsonObj));
        }

        window.addEventListener("keydown", checkKeyPress);

        function checkKeyPress(key) {
            if (key.keyCode === 87) {
                jsonObj = "UP";
            }
            if (key.keyCode === 65) {
                jsonObj = "LEFT";
            }
            if (key.keyCode === 68) {
                jsonObj = "RIGHT";
            }
            if (key.keyCode === 83) {
                jsonObj = "DOWN";
            }
            chatClient.send(JSON.stringify(jsonObj));
        }
    </script>

    <style>
        body {
            background-color: RGB(24, 24, 24);
            color: #22acff;
            margin: 0;
        }

        #field {
            margin-left: 40px;
            margin-top: 10px;
            width: 1200px;
            height: 600px;
        }
    </style>
</head>


<body onload="connect()" onunload="disconnect()">
<svg id='field'>
    <%
//        for (int i = 0; i < 100; i += 20) {
//            out.println("<rect x=" + i + " y=0 width=10 height=10 fill='orange' />");
//        }
//        for (Pipeline pipeline : GameServerEndPoint.getPipelines()) {
//            for (House house : pipeline.getHouses()) {
//                for (Node node : house.getHouseFragments()) {
//                    int i = node.getI()*10;
//                    int j = node.getJ()*10;
//                    out.println("<rect x=" + j + " y=" + i + " width=10 height=10 fill='orange' />");
//                }
//            }
//        }
    %>
</svg>
</body>
</html>


