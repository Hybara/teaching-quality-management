const INDEX_PAGE = 1;
const PAGINATION_PAGINTEM_NUMBER = 5;

var question_count;

$(function () {

  {
    let result = $("h1.page-header").find("small").text();
    if (result == "notAnswer") {
      $("h1.page-header").find("small").text("未终止的提问");
    } else if (result == "perfect") {
      $("h1.page-header").find("small").text("优");
    } else if (result == "good") {
      $("h1.page-header").find("small").text("良");
    } else if (result == "medium") {
      $("h1.page-header").find("small").text("中");
    } else if (result == "dissatisfactory") {
      $("h1.page-header").find("small").text("不够理想");
    }
  }

  initQuestionList(INDEX_PAGE);

  $("ul.pagination a").on("click", function () {
    var page_count = question_count;

    var page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«" == page) {
      var now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now > 1) ? (now - 1) : 1;
    } else if ("»" == page) {
      var now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
    } else if ($(this).attr("id") == "first") {
      page = INDEX_PAGE;
    } else if ($(this).attr("id") == "end") {
      page = page_count;
    }
    initQuestionList(page);
  });

  $("input[type=checkbox]").attr("checked", false);
  $("input[type=checkbox]").on("click", function (event) {
    if ($(event.target).attr("checked")) {
      $(event.target).attr("checked", false);
    } else {
      $(event.target).attr("checked", true);
      $(event.target).parent().siblings().find("input[type=checkbox]").attr(
          "checked", false);
    }
  });

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();

    let token = $("body").attr("data-token");
    let questionId = $("h1.page-header").attr("data-id");
    let flag = $("input[type=checkbox][name=flag]").attr("checked") ? true
        : false;

    let data = {
      token: token,
      questionId: questionId,
      text: $("textarea[name=text]").val(),
      flag: flag
    };
    $.post("/teacher/resultQuestion", data, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
      } else {
        window.location.href = "/teacher/goQuestion/" + $("div.header").attr(
            "data-id") + "/" + $("li#score").attr(
            "data-id") + "/" + questionId + "/" + token;
      }
    }, "text");
  });

});

function initQuestionList(page) {
  let token = $("body").attr("data-token");
  let questionId = $("h1.page-header").attr("data-id");

  $.post("/teacher/getQuestionLists", {
    token: token,
    questionId: questionId,
    page: page
  }, function (response) {
    console.log("questionChilds", response);
    initPageButton(response.count, page);
    question_count = response.count;

    let $liItems = $("li.child");
    if (question_count == 0) {
      $liItems.hide();
      $("ul.pagination").hide();
      return;
    }
    $liItems.show();
    $liItems.each(function (index) {
      if (response.data[index] != undefined
          && response.data[index] != null) {
        $(this).show();
        initQuestionPanel($(this), response.data[index]);
      } else {
        $(this).hide();
      }
    });
  }, "json");
}

function initPageButton(count, page) {
  let $pagination = $("ul.pagination");
  $pagination.show();
  if (count == 0) {
    $pagination.hide();
    return;
  }
  $pagination.find("li.pageItem").hide();

  let page_count = count;
  let for_turn;
  let page_start;
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

  for (let i = 0; i < for_turn; i++) {
    $($pagination.find("li.pageItem")[i]).show().find("a").text(page_start);
    $($pagination.find("li.pageItem")[i]).removeClass("active");
    if (page_start == page) {
      $($pagination.find("li.pageItem")[i]).addClass("active");
    }
    page_start++;
  }
}

function initQuestionPanel($li, data) {
  $li.find("img").attr("src", data.userHeader);
  $li.find("div.media-left span").text(data.userName);
  $li.find("div.media-body p").text(data.text);
  $li.find("div.media-body div").text(format(new Date(data.createTime)));
}

function format(date) {
  let monthNum = parseInt(date.getMonth()) + 1;
  if (monthNum < 10) {
    monthNum = "0" + monthNum;
  } else {
    monthNum = "" + monthNum;
  }
  let time = formatTime(date.getHours()) + ":" + formatTime(date.getMinutes())
      + ":" + formatTime(date.getSeconds());
  return date.getFullYear() + "-" + monthNum + "-" + formatTime(date.getDate())
      + " " + time;
}

function formatTime(time) {
  if (parseInt(time) < 10) {
    return "0" + time;
  }
  return time;
}