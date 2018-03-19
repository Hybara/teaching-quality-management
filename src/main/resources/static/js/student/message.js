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
    } else if ("»" == page) {
      var now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
    } else if ($(this).attr("id") == "first") {
      page = 1;
    } else if ($(this).attr("id") == "end") {
      page = page_count;
    }
    initMessageList(type, page);
  });

  $(".collapse").on("show.bs.collapse", function () {

    var id = $($(this).siblings("div.panel-heading")[0]).find(
        "a.collapsed").attr("data-id");
    var $collapsed = $($(this).siblings("div.panel-heading")[0]).find(
        "a.collapsed");
    var token = $("body").attr("data-token");
    $.post("/student/readMessage", {
      token: token,
      id: id
    }, function (response) {
      if (response == "ok") {
        $collapsed.removeClass("noread");
      } else if (response == "logout") {
        window.location.href = "/logout/" + token;
      }
    }, "text");
  });

  $(".readAllMessage").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    var token = $("body").attr("data-token");
    if (confirm("这将把所有公告标记为已读，您确认要这么做？")) {
      var token = $("body").attr("data-token");
      $.post("/student/readAllMessages", {token: token}, function (response) {
        if (response == "ok") {
          alert("操作成功");
          window.location.href = "/student/message/" + token;
        } else if (response == "none") {
          alert("操作失败");
        } else if (response == "logout") {
          window.location.href = "/logout/" + token;
        }
      }, "text");
    }
  });

  $("a.del").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    var token = $("body").attr("data-token");
    var id = $($(this).siblings("a.collapsed")[0]).attr("data-id");
    if (confirm("这将删除这条公告，您确认要这么做？")) {
      var token = $("body").attr("data-token");
      $.post($(this).attr("href"), {
        token: token,
        id: id,
      }, function (response) {
        if (response == "ok") {
          alert("删除成功");
          window.location.href = "/student/message/" + token;
        } else if (response == "none") {
          alert("删除失败");
        } else if (response == "logout") {
          window.location.href = "/logout/" + token;
        }
      }, "text");
    }
  });

  $(".clearMessage").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    var token = $("body").attr("data-token");
    var message;
    if (type == ALL_MESSAGE_TYPE) {
      message = "这将删除所有公告，您确认要这么做？";
    } else {
      message = "这将删除所有未读公告，您确认要这么做？";
    }
    if (confirm(message)) {
      var token = $("body").attr("data-token");
      $.post("/student/delAllMessages", {
        token: token,
        type: type
      }, function (response) {
        if (response == "ok") {
          alert("删除成功");
          window.location.href = "/student/message/" + token;
        } else if (response == "none") {
          alert("删除失败");
        } else if (response == "logout") {
          window.location.href = "/logout/" + token;
        }
      }, "text");
    }
  });
});

function initMessageList(type, page) {
  var token = $("body").attr("data-token");
  $.ajax({
    url: "/student/getMessages/" + token,
    type: "post",
    method: "post",
    dataType: "json",
    data: {
      page: page,
      type: type
    },
    success: function (messageList) {
      if (type == ALL_MESSAGE_TYPE) {
        type_all_page_count = messageList.count;
      } else {
        type_noread_page_count = messageList.count;
      }
      console.log("page count", messageList.count);
      console.log("list", messageList.data);
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
          $item.hide();
        } else {
          $item.show();
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

  var page_count;
  if (type == ALL_MESSAGE_TYPE) {
    page_count = type_all_page_count;
  } else {
    page_count = type_noread_page_count;
  }
  var for_turn;
  var page_start;
  if (count < PAGINATION_PAGINTEM_NUMBER) {
    for_turn = count;
    page_start = 1;
  } else if (page < PAGINATION_PAGINTEM_NUMBER) {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = 1;
  } else if (page + 2 >= page_count) {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = page_count - PAGINATION_PAGINTEM_NUMBER + 1;
  } else {
    for_turn = PAGINATION_PAGINTEM_NUMBER;
    page_start = page - 2;  // 保证此时page的显示位于最中间
  }

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
  if (message.flag) {
    $item.find(".collapsed").html(message.title).attr("data-id",
        message.id).removeClass("noread");
  } else {
    $item.find(".collapsed").html(message.title).attr("data-id",
        message.id).addClass("noread");
  }
  $item.find(".panel-body").text(message.content);
  $item.find("a.del").attr("href", "/student/delMessage/");
}
