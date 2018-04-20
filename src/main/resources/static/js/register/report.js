const DEFAULT_PAGE = 1;
const PAGINATION_PAGINTEM_NUMBER = 5;

let token = $("body").attr("data-token");
let page_count;
$(function () {

  initReportList(DEFAULT_PAGE);

  $("ul.pagination a").on("click", function () {

    let page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«" == page) {
      let now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now > 1) ? (now - 1) : 1;
    } else if ("»" == page) {
      let now = $(this).parents("ul.pagination").find("li.active").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
    } else if ($(this).attr("id") == "first") {
      page = 1;
    } else if ($(this).attr("id") == "end") {
      page = page_count;
    }
    initReportList(page);
  });

  $("a.allow").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let id = $(this).parent().parent().attr("id");
    $.post("/register/report/allow", {
      token: token,
      reportId: id
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/"+token;
      } else {
        let page = $("ul.pagination").find("li.active").text();
        initReportList(page);
      }
    }, "text");
  });

  $("a.notAllowed").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let id = $(this).parent().parent().attr("id");
    $.post("/register/report/notAllowed", {
      token: token,
      reportId: id
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/"+token;
      } else {
        let page = $("ul.pagination").find("li.active").text();
        initReportList(page);
      }
    }, "text");
  });

});

function initReportList(page) {
  $.post("/register/getReport", {
    page: page,
    token: token
  }, function (response) {

    if (response.count == -1) {
      window.location.href = "/logout/"+token;
    }
    page_count = response.count;
    $("tbody tr").each(function (index) {
      if (response.data[index] != undefined || response.data[index]!= null) {
        $(this).attr("id", response.data[index].id);
        $(this).find("td.content").text(response.data[index].content);
        $(this).find("td.reason").text(response.data[index].reason);
        $(this).show();
      } else {
        $(this).hide();
      }

    });
    initPageButton(response.count, page);
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
