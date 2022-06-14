$(function (){

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
        if (data === "success") {
          alert("Send success!!!")
        } else {
          alert("Send message fail!!!");
          return false;
        }
      }
    });
  }

});