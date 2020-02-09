
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host+ webCtx + "/game";
var chatClient = null;

function connect () {
    chatClient = new WebSocket(endPointURL);
}

function disconnect () {
    chatClient.close();
}

