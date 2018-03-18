var DEFAULT_MESSAGE_TYPE = "all";
var ALL_MESSAGE_TYPE = "all";
var NOREAD_MESSAGE_TYPE = "noread";
var DEFAULT_MESSAGE_PAGE = 1;
var PAGINATION_PAGINTEM_NUMBER = 5;

var type_all_page_count = 0;
var type_noread_page_count = 0;
var type = ALL_MESSAGE_TYPE;

$(function () {
  initMessageList(DEFAULT_MESSAGE_TYPE, DEFAULT_MESSAGE_PAGE);

  $("a[data-toggle=tab]").on("click", function () {
    type = $(this).attr("href");
    type = type.substring(1, type.length);
    initMessageList(type, DEFAULT_MESSAGE_PAGE);
  });

  $("ul.pagination a").on("click", function () {
    var page_count;
    if (type == ALL_MESSAGE_TYPE) {
      page_count = type_all_page_count;
    } else {
      page_count = type_noread_page_count;
    }
    var page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«" == page) {
      var now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now > 1) ? (now - 1) : 1;
      console.log("1,page", page);
    } else if ("»" == page) {
      var now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
      console.log("2,page", page);
    } else if ($(this).attr("id") == "first") {
      page = 1;
      console.log("3,page", page);
    } else if ($(this).attr("id") == "end") {
      page = page_count;
      console.log("4,page", page);
    }
    initMessageList(type, page);
  });
});

function initMessageList(type, page) {
  var token = $("body").attr("data-token");
  $.ajax({
    url: "/student/getMessages/" + token + "?page=" + page + "&type=" + type,
    type: "post",
    method: "post",
    dataType: "json",
    data: "",
    success: function (messageList) {
      if (type == ALL_MESSAGE_TYPE) {
        type_all_page_count = messageList.count;
      } else {
        type_noread_page_count = messageList.count;
      }
      initPageButton(messageList.count, page);
      var $items;
      if (type == ALL_MESSAGE_TYPE) {
        $items = $(".message-all");
      } else {
        $items = $(".message-noread");
      }
      $items.show();
      for (var i = 0; i < $items.length; i++) {
        var $item = $($items.get(i));
        if (messageList.data[i] == undefined || messageList.data[i] == null) {
          $item.css("display", "none");
        } else {
          initMessagePanel($item, messageList.data[i], token);
        }
      }
    }
  })
}

function initPageButton(count, page) {
  var $pagination;
  if (type == ALL_MESSAGE_TYPE) {
    $pagination = $("div#all").find("ul.pagination");
    type_all_page_count = count;
  } else {
    $pagination = $("div#noread").find("ul.pagination");
    type_noread_page_count = count;
  }
  $pagination.show();
  if (count == 0) {
    $pagination.hide();
  }
  $pagination.find("li.pageItem").hide();
  console.log("page", page);

  var page_count;
  if (type == ALL_MESSAGE_TYPE) {
    page_count = type_all_page_count;
  } else {
    page_count = type_noread_page_count;
  }
  var for_turn;
  var page_start;
  if (count<PAGINATION_PAGINTEM_NUMBER) {
    for_turn = count;
    page_start = 1;
  } else if (page<PAGINATION_PAGINTEM_NUMBER) {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = 1;
  } else if (page+2>=page_count) {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = page_count - PAGINATION_PAGINTEM_NUMBER + 1;
  } else {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = page - 2;  // 保证此时page的显示位于最中间
  }

  console.log("for_turn", for_turn, "type", typeof for_turn);
  console.log("page_start", page_start, "type", typeof page_start);

  for (var i = 0; i < for_turn; i++) {
    $($pagination.find("li.pageItem")[i]).show().find("a").text(page_start);
    $($pagination.find("li.pageItem")[i]).removeClass("active");
    if (page_start == page) {
      $($pagination.find("li.pageItem")[i]).addClass("active");
    }
    page_start++;
  }
}

function initMessagePanel($item, message, token) {
  // console.log("flag", message.flag);
  if (message.flag) {
    $item.find(".collapsed").html(message.title);
  } else {
    $item.find(".collapsed").html(message.title).addClass("noread").css(
        "font-weight", "bold");
  }
  $item.find(".panel-body").text(message.content);
  $item.find("a.del").attr("href", "/student/delMessage/" + token + "/"
      + message.id);
}
