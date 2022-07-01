$(function (){

  // 每隔两秒钟获取聊天列表 -- 轮训的方式获取消息
  // setInterval(getMessage, 2000);
  // setInterval(getUserList, 2000);

  // 定义webSocket客户端连接对象  -- websocket方式
  var websocket = null;

  initWebsocket();

  function initWebsocket() {
    // 向服务器发送连接
    websocket = new WebSocket("ws://localhost:8080/c_s_s_jsp/websocket")

    // 连接成功
    websocket.onopen = function () {
    }

    // 收到服务器的消息
    websocket.onmessage = function (event) {
      $("#divCount").html(event.data);
    }

    // 关闭
    websocket.onclose = function () {
    }
  }

    function websocketSendMessage() {

    }

  // 发送消息的点击事件
  $("#button1").click(function () {
    var content = $("#txtContent").val();
    if (content !== "") {
      // 发送ajax消息
      // sendMessage(content);
    } else {
      alert("Send content can't empty!!!");
      return false;
    }
  });

  // send message（ajax）
  function sendMessage(content) {
    $.ajax({
      type: "post",
      url: "ChatServlet",
      data : {
        op : "send",
        msg: content
      },
      success: function (data) {
        // if (data === "success") {
        //   // alert("Send success!!!")
        //   $("#divCount").html(data);
        // } else {
        //   alert("Send message fail!!!");
        //   return false;
        // }
        $("#divCount").html(data);
        $("#txtContent").val("");
      }
    });
  }

  // get message
  function getMessage() {
    $.ajax({
      type: "post",
      url: "ChatServlet",
      data : {
        op : "get"
      },
      success: function (data) {
        $("#divCount").html(data);
      }
    });
  }

  // get user list
  function getUserList() {
    $.ajax({
      type: "post",
      url: "ChatServlet",
      data : {
        op : "userList"
      },
      success: function (data) {
        $("#divOnline").html(data);
      }
    });
  }

});