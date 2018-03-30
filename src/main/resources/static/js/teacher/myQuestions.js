const INDEX_PAGE = 1;
const DEFAULT_TYPE = "all";
const QUESTION_ALL_TYPE = "all";
const QUESTION_PERFECT_TYPE = "perfect";
const QUESTION_GOOD_TYPE = "good";
const QUESTION_MEDIUM_TYPE = "medium";
const QUESTION_DISSATISFACTORY_TYPE = "dissatisfactory";
const QUESTION_NOTANSWER_TYPE = "notAnswer";
const PAGINATION_PAGINTEM_NUMBER = 5;

var question_type = DEFAULT_TYPE;
var question_all_count;
var question_perfect_count;
var question_good_count;
var question_medium_count;
var question_dissatisfactory_count;
var question_notAnswer_count;

$(function () {

  initQuestionList(DEFAULT_TYPE, INDEX_PAGE);

  $("ul.pagination a").on("click", function () {
    var page_count;
    if (question_type == QUESTION_ALL_TYPE) {
      page_count = question_all_count;
    } else if (question_type == QUESTION_PERFECT_TYPE) {
      page_count = question_perfect_count;
    } else if (question_type == QUESTION_GOOD_TYPE) {
      page_count = question_good_count;
    } else if (question_type == QUESTION_MEDIUM_TYPE) {
      page_count = question_medium_count;
    } else if (question_type == QUESTION_DISSATISFACTORY_TYPE) {
      page_count = question_dissatisfactory_count;
    } else if (question_type == QUESTION_NOTANSWER_TYPE) {
      page_count = question_notAnswer_count;
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
      page = INDEX_PAGE;
    } else if ($(this).attr("id") == "end") {
      page = page_count;
    }
    initQuestionList(question_type, page);
  });

  $("a[data-toggle=tab]").on("click", function () {
    debugger;
    let type = $(this).attr("href");
    type = type.substring(1, type.length);
    initQuestionList(type, INDEX_PAGE);
  });

});

function initQuestionList(type, page) {
  let token = $("body").attr("data-token");
  $.post("/teacher/getScoreQuestions", {
    token: token,
    scoreForClassId: $("h1.page-header").attr("data-id"),
    type: type,
    page: page
  }, function (response) {
    console.log("questions", response);

    question_type = type;
    initPageButton(response.count, page);
    ininPageCount(response.count);

    let $liItems = structuralQuestionLiDOM(type);
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
  let $pagination = structuralPainationDOM(question_type);
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

function structuralPainationDOM(type) {
  if (type == QUESTION_ALL_TYPE) {
    return $("div#all").find("ul.pagination");
  } else if (type == QUESTION_PERFECT_TYPE) {
    return $("div#perfect").find("ul.pagination");
  } else if (type == QUESTION_GOOD_TYPE) {
    return $("div#good").find("ul.pagination");
  } else if (type == QUESTION_MEDIUM_TYPE) {
    return $("div#medium").find("ul.pagination");
  } else if (type == QUESTION_DISSATISFACTORY_TYPE) {
    return $("div#dissatisfactory").find("ul.pagination");
  } else if (question_type == QUESTION_NOTANSWER_TYPE) {
    return $("div#notAnswer").find("ul.pagination");
  }
}

function ininPageCount(count) {
  if (question_type == QUESTION_ALL_TYPE) {
    question_all_count = count;
  } else if (question_type == QUESTION_PERFECT_TYPE) {
    question_perfect_count = count;
  } else if (question_type == QUESTION_GOOD_TYPE) {
    question_good_count = count;
  } else if (question_type == QUESTION_MEDIUM_TYPE) {
    question_medium_count = count;
  } else if (question_type == QUESTION_DISSATISFACTORY_TYPE) {
    question_dissatisfactory_count = count;
  } else if (question_type == QUESTION_NOTANSWER_TYPE) {
    question_notAnswer_count = count;
  }
}

function structuralQuestionLiDOM(type) {
  if (type == QUESTION_ALL_TYPE) {
    return $("div#all").find("li.list-group-item");
  } else if (type == QUESTION_PERFECT_TYPE) {
    return $("div#perfect").find("li.list-group-item");
  } else if (type == QUESTION_GOOD_TYPE) {
    return $("div#good").find("li.list-group-item");
  } else if (type == QUESTION_MEDIUM_TYPE) {
    return $("div#medium").find("li.list-group-item");
  } else if (type == QUESTION_DISSATISFACTORY_TYPE) {
    return $("div#dissatisfactory").find("li.list-group-item");
  } else if (type == QUESTION_NOTANSWER_TYPE) {
    return $("div#notAnswer").find("li.list-group-item");
  }
}

function initQuestionPanel($li, data) {
  let token = $("body").attr("data-token");
  let scoreId = $("h1.page-header").attr("data-id");
  $li.find("img").attr("src", data.userHeader);
  $li.find("div.media-left span").text(data.userName);
  $li.find("div.media-body h4").find("a").attr("href", "/teacher/goQuestion/"
      + scoreId + "/" + data.id + "/" + token).text(
      data.title == null ? "" : data.title);
  $li.find("div.media-body p").find("a").attr("href", "/teacher/goQuestion/"
      + scoreId + "/" + data.id + "/" + token).text(data.text);
  structuralResult($li, data.result);
}

function structuralResult($li, result) {
  if (result == QUESTION_PERFECT_TYPE) {
    $li.find("div.media-body div").text("评价：优").addClass(
        "text-success").removeClass("text-info text-warning text-danger");
  } else if (result == QUESTION_GOOD_TYPE) {
    $li.find("div.media-body div").text("评价：良").addClass(
        "text-info").removeClass("text-success text-warning text-danger");
  } else if (result == QUESTION_MEDIUM_TYPE) {
    $li.find("div.media-body div").text("评价：中").addClass(
        "text-warning").removeClass("text-info text-success text-danger");
  } else if (result == QUESTION_DISSATISFACTORY_TYPE) {
    $li.find("div.media-body div").text("评价：不够理想").addClass(
        "text-danger").removeClass("text-info text-warning text-success");
  } else if (result == QUESTION_NOTANSWER_TYPE) {
    $li.find("div.media-body div").text("未终止的问答").addClass(
        "text-danger").removeClass("text-info text-warning text-success");
  }
}
