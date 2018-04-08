const INDEX_PAGE = 1;
const NO_SEARCH = null;
const PAGINATION_PAGINTEM_NUMBER = 5;

let search = NO_SEARCH;
let page_count;

$(function () {

  initTemplateList(INDEX_PAGE, NO_SEARCH);

  $(".pagination a").on("click", function () {
    let page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«" == page) {
      let now = $("ul.pagination li.active a").text();
      page = (now > 1) ? (now - 1) : 1;
    } else {
      let now = $("ul.pagination li.active a").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
    }
    initTemplateList(page, search);
  });

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    search = $("#search").val();
    initTemplateList(INDEX_PAGE, search);
    return false;
  });

  $("button[type=button]").on("click", function () {
    $("#search").val("");
    initTemplateList(INDEX_PAGE, NO_SEARCH);
  });
});

function initTemplateList(page, search) {
  let token = $("body").attr("data-token");
  $.post("/register/questionnaire/getTemplate", {
    token: token,
    page: page,
    search: search
  }, function (response) {
    console.log("response", response);
    $("#major").find("div.col-sm-3").hide();
    if (response == undefined || response.count == undefined || response.data
        == undefined) {
      return;
    }
    page_count = response.count;
    $("#major").find("div.col-sm-3").each(function (index, div) {
      if (response.data[index] == undefined || response.data[index] == null) {
        $(div).hide();
      } else {
        $(div).show();
        $(div).find("a").css("font-size", "12px").attr(
            "href", "/register/teacherList/" + response.data[index].id + "/"
            + token);
        $(div).find("a").find("span.number").text(response.data[index].id);
        $(div).find("a").find("span.name").text(response.data[index].name);
      }
      initPageButton(page_count, page);
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
