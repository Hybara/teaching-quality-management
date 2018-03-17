var DEFAULT_MESSAGE_TYPE = "all";
var ALL_MESSAGE_TYPE = "all";
var NOREAD_MESSAGE_TYPE = "noread";
var DEFAULT_MESSAGE_PAGE = 1;

$(function () {
  initMessageList(DEFAULT_MESSAGE_TYPE, DEFAULT_MESSAGE_PAGE);

  $("a[data-toggle=tab]").on("click", function () {
    var type = $(this).attr("href");
    type = type.substring(1, type.length);
    initMessageList(type, DEFAULT_MESSAGE_PAGE);
  });
});

function initMessageList(type, page) {
  var token = $("body").attr("data-token");
  $.ajax({
    url: "/student/getMessages/"+token+"?page="+page+"&type="+type,
    type: "post",
    method: "post",
    dataType: "json",
    data: "",
    success: function (messageList) {
      page_count = messageList.count;
      // initPageButton(page_count, page);
      var $items;
      if (type == ALL_MESSAGE_TYPE) {
        $items = $(".message-all");
      } else {
        $items = $(".message-noread");
      }
      $items.show();
      for(var i=0; i<$items.length; i++) {
        var $item = $($items.get(i));
        if (messageList.data[i]==undefined || messageList.data[i]==null) {
          $item.css("display", "none");
        } else {
          initMessagePanel($item, messageList.data[i], token);
        }
      }
    }
  })
}

function initMessagePanel($item, message, token) {
  $item.find(".collapsed").text(message.title);
  $item.find(".panel-body").text(message.content);
  $item.find("a.del").attr("href", "/student/delMessage/"+token+"/"+message.id);
}
