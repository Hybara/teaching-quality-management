const DEFAULT_PAGE = 1;
const PAGINATION_PAGINTEM_NUMBER = 5;
const DEFAULT_SEARCH = null;

let page_count;
let now_search = DEFAULT_SEARCH;
let token = $("body").attr("data-token");
$(function () {

  initReportList(DEFAULT_PAGE, DEFAULT_SEARCH);

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
    initReportList(page, now_search);
  });

  $("a.update").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let id = $(this).parent().parent().attr("id");
    window.location.href = "/admin/goUpdateMajor/"+id+"/" + token;
  });


  $("a.delete").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let id = $(this).parent().parent().attr("id");

    $.post("/admin/deleteMajor", {
      id: id,
      token: token
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/"+token;
      } else {
        initReportList($("ul.pagination").find("li.active").text(), now_search);
      }
    }, "text")
  });

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let search = $("input#search").val();
    if (search=="") {
      search == null;
    }
    initReportList(DEFAULT_PAGE, search);
  });

  $("button.all").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let search = null;
    initReportList(DEFAULT_PAGE, search);
  });

  $("button.add").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    window.location.href = "/admin/goAddMajor/" + token;
  });

});

function initReportList(page, search) {
  $.post("/admin/getMajor", {
    page: page,
    search: search
  }, function (response) {
    page_count = response.count;
    now_search = search;
    $("tbody tr").each(function (index) {
      if (response.data[index] != undefined || response.data[index]!= null) {
        $(this).attr("id", response.data[index].id);
        $(this).find("td.number").text(response.data[index].number);
        $(this).find("td.name").text(response.data[index].name);
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
