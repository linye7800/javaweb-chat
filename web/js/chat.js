$(function (){

  // 每隔两秒钟获取聊天列表
  setInterval(getMessage, 2000);

  // 发送消息的点击事件
  $("#button1").click(function () {
    var content = $("#txtContent").val();
    if (content !== "") {
      // 发送ajax消息
      sendMessage(content);
    } else {
      alert("Send content can't empty!!!");
      return false;
    }
  });

  // send message
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

  // send message
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



});