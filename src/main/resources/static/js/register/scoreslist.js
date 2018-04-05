const INDEX_PAGE = 1;
const NO_SEARCH = null;
const LIST_SIZE = 12;

let search = NO_SEARCH;
let page_count;

$(function () {

  initTeacherList(INDEX_PAGE, NO_SEARCH);

  $(".pagination a").on("click", function () {
    let page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«" == page) {
      var now = $("ul.pagination li.active a").text();
      page = (now > 1) ? (now - 1) : 1;
    } else {
      var now = $("ul.pagination li.active a").text();
      page = (now == page_count) ? page_count : (parseInt(now) + 1);
    }
    initTeacherList(page, search);
  });

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    search = $("input").val();
    if (search == "") {
      search == null;
    }
    initTeacherList(INDEX_PAGE, search);
  });

  $("button[type=button]").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    search = null;
    $("input").val("");
    initTeacherList(INDEX_PAGE, search);
  });
});

function initTeacherList(page, search) {
  let token = $("body").attr("data-token");
  let majorId = $("div.major").attr("data-id");
  $.post("/register/getMajorScores", {
    token: token,
    page: page,
    search: search,
    majorId: majorId
  }, function (response) {
    $(".table tbody").hide();
    if (response == undefined || response == null ||
        response.data == undefined || response.data == null) {
      return;
    }
    page_count = response.count;
    $(".table tbody").show();
    $(".table tbody tr").each(function (tr_index, tr) {
      if (response.data[tr_index] != undefined
          && response.data[tr_index] != null) {
        $(tr).show().on("click", function () {
          window.location.href
              = "/register/goMajorScores/" + majorId + "/"
              + response.data[tr_index].id + "/" + token;
        });
        initTableLine($(tr), response.data[tr_index], tr_index, page);
      } else {
        $(tr).hide();
      }
      initPageButton(page_count, page);
    });
  }, "json");
}

function initTableLine($tr, data, index, page) {
  console.log("data", data);
  $tds = $tr.find("td");
  $($tds.get(0)).text(index + 1 + (page - 1) * LIST_SIZE);
  $($tds.get(1)).text(data.scoreNumber);
  $($tds.get(2)).text(data.scoreName);
  $($tds.get(3)).text(data.scoreTypeName);
  $($tds.get(4)).text(data.scoreCredit);
  $($tds.get(5)).text(data.scoreHours);
  $($tds.get(6)).text(data.scoreTestWay);
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