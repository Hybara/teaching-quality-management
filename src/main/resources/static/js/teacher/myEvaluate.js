const INDEX_PAGE = 1;
const DEFAULT_TYPE = "all";
const EVALUATE_ALL_TYPE = "all";
const EVALUATE_PERFECT_TYPE = "perfect";
const EVALUATE_GOOD_TYPE = "good";
const EVALUATE_MEDIUM_TYPE = "medium";
const EVALUATE_DISSATISFACTORY_TYPE = "dissatisfactory";
const PAGINATION_PAGINTEM_NUMBER = 5;

var evaluate_type = DEFAULT_TYPE;
var evaluate_all_count;
var evaluate_perfect_count;
var evaluate_good_count;
var evaluate_medium_count;
var evaluate_dissatisfactory_count;

$(function () {

  initEvaluateList(DEFAULT_TYPE, INDEX_PAGE);

  $("ul.pagination a").on("click", function () {
    var page_count;
    if (evaluate_type == EVALUATE_ALL_TYPE) {
      page_count = evaluate_all_count;
    } else if (evaluate_type == EVALUATE_PERFECT_TYPE) {
      page_count = evaluate_perfect_count;
    } else if (evaluate_type == EVALUATE_GOOD_TYPE) {
      page_count = evaluate_good_count;
    } else if (evaluate_type == EVALUATE_MEDIUM_TYPE) {
      page_count = evaluate_medium_count;
    } else if (evaluate_type == EVALUATE_DISSATISFACTORY_TYPE) {
      page_count = evaluate_dissatisfactory_count;
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
    initEvaluateList(evaluate_type, page);
  });

  $("a[data-toggle=tab]").on("click", function () {
    let type = $(this).attr("href");
    type = type.substring(1, type.length);
    initEvaluateList(type, INDEX_PAGE);
  });

});

function initEvaluateList(type, page) {
  let token = $("body").attr("data-token");

  $.post("/teacher/getScoreEvaluates", {
    token: token,
    scoreForClassId: $("h1.page-header").attr("data-id"),
    type: type,
    page: page
  }, function (response) {
    evaluate_type = type;
    initPageButton(response.count, page);
    ininPageCount(response.count);

    let $liItems = structuralEvaluateLiDOM(type);
    $liItems.show();
    $liItems.each(function (index) {
      if (response.data[index] != undefined
          && response.data[index] != null) {
        $(this).show();
        initEvaluatePanel($(this), response.data[index]);
      } else {
        $(this).hide();
      }
    });
  }, "json");
}

function initPageButton(count, page) {
  let $pagination = structuralPainationDOM(evaluate_type);
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
  if (type == EVALUATE_ALL_TYPE) {
    return $("div#all").find("ul.pagination");
  } else if (type == EVALUATE_PERFECT_TYPE) {
    return $("div#perfect").find("ul.pagination");
  } else if (type == EVALUATE_GOOD_TYPE) {
    return $("div#good").find("ul.pagination");
  } else if (type == EVALUATE_MEDIUM_TYPE) {
    return $("div#medium").find("ul.pagination");
  } else if (type == EVALUATE_DISSATISFACTORY_TYPE) {
    return $("div#dissatisfactory").find("ul.pagination");
  }
}

function ininPageCount(count) {
  if (evaluate_type == EVALUATE_ALL_TYPE) {
    evaluate_all_count = count;
  } else if (evaluate_type == EVALUATE_PERFECT_TYPE) {
    evaluate_perfect_count = count;
  } else if (evaluate_type == EVALUATE_GOOD_TYPE) {
    evaluate_good_count = count;
  } else if (evaluate_type == EVALUATE_MEDIUM_TYPE) {
    evaluate_medium_count = count;
  } else if (evaluate_type == EVALUATE_DISSATISFACTORY_TYPE) {
    evaluate_dissatisfactory_count = count;
  }
}

function structuralEvaluateLiDOM(type) {
  if (type == EVALUATE_ALL_TYPE) {
    return $("div#all").find("li.list-group-item");
  } else if (type == EVALUATE_PERFECT_TYPE) {
    return $("div#perfect").find("li.list-group-item");
  } else if (type == EVALUATE_GOOD_TYPE) {
    return $("div#good").find("li.list-group-item");
  } else if (type == EVALUATE_MEDIUM_TYPE) {
    return $("div#medium").find("li.list-group-item");
  } else if (type == EVALUATE_DISSATISFACTORY_TYPE) {
    return $("div#dissatisfactory").find("li.list-group-item");
  }
}

function initEvaluatePanel($li, data) {
  $li.find("img").attr("src", data.userHeader);
  $li.find("div.media-left span").text(data.userName);
  $li.find("div.media-body h4").text(data.title == null ? "" : data.title);
  $li.find("div.media-body p").text(data.text);
  structuralResult($li, data.result);
}

function structuralResult($li, result) {
  if (result == EVALUATE_PERFECT_TYPE) {
    $li.find("div.media-body div").text("评价：优").addClass(
        "text-success").removeClass("text-info text-warning text-danger");
  } else if (result == EVALUATE_GOOD_TYPE) {
    $li.find("div.media-body div").text("评价：良").addClass(
        "text-info").removeClass("text-success text-warning text-danger");
  } else if (result == EVALUATE_MEDIUM_TYPE) {
    $li.find("div.media-body div").text("评价：中").addClass(
        "text-warning").removeClass("text-info text-success text-danger");
  } else if (result == EVALUATE_DISSATISFACTORY_TYPE) {
    $li.find("div.media-body div").text("评价：不够理想").addClass(
        "text-danger").removeClass("text-info text-warning text-success");
  }
}
