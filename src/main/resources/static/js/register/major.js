const INDEX_PAGE = 1;
const NO_SEARCH = null;

let search = NO_SEARCH;
let page_count;

$(function () {

  initMajorList(INDEX_PAGE, NO_SEARCH);

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
    initMajorList(page, search);
  });

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    search = $("#search").val();
    initMajorList(INDEX_PAGE, search);
    return false;
  });

  $("button[type=button]").on("click", function () {
    $("#search").val("");
    initMajorList(INDEX_PAGE, NO_SEARCH);
  });
});

function initMajorList(page, search) {
  let token = $("body").attr("data-token");
  $.post("/register/getMajors", {
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
        $(div).find("a").find("span.number").text(response.data[index].number);
        $(div).find("a").find("span.name").text(response.data[index].name);
      }
      initPageButton(page_count, page);
    });
  }, "json");

}

function initPageButton(count, page) {
  if (count == 0) {
    $("ul.pagination li").hide();
    return;
  }
  $("ul.pagination li").show();
  $("ul.pagination li.pageItem").hide();
  // console.log("count", count);
  for (var i = 0; i < count; i++) {
    $($("ul.pagination li.pageItem")[i]).show().removeClass("active");
  }
  $($("ul.pagination li").get(page)).addClass("active");
}