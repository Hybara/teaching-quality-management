const INDEX_PAGE = 1;
const PAGINATION_PAGINTEM_NUMBER = 5;

let type;
let token = $("body").attr("data-token");
let page_count;

$(function () {

  $("ul.pagination a").on("click", function () {
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
    initQuestionnaireBank($("select[name=type]").val(), page);
  });

  $("select[name=type]").on("change", function () {
    let typeId = $(this).val();
    initQuestionnaireBank($("select[name=type]").val(), INDEX_PAGE);
  });

  $("button.select").on("click", function () {
    let oldQuestion = $("li#question").attr("data-id");
    let newQuestion = $(this).attr("id");
    let token = $("body").attr("data-token");
    let templateId = $("h1.page-header").attr("data-id");

    $.post("/register/template/changeTemplateQuestion", {
      templateId: templateId,
      oldQuestion: oldQuestion,
      newQuestion: newQuestion,
      token: token
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
      } else if (response == "ok") {
        alert("修改成功！");
      } else if (response == "failure") {
        alert("修改失败！");
      }
      window.location.href = "/register/template/goQuestionnaire/"
          + templateId + "/" + token;
    }, "text");
  });

  initQuestionnaireBank($("select[name=type]").val(), INDEX_PAGE);

});

function initQuestionnaireBank(typeId, page) {
  $.post("/register/template/getQuestionBank", {
    template: $("h1.page-header").attr("data-id"),
    type: typeId,
    token: token,
    page: page
  }, function (response) {
    console.log("response", response);
    type = typeId;
    page_count = response.count;
    $("ul.list-group").hide();
    $("ul.pagination").hide();
    if (response.data==undefined || response.data==null || response.count==0) {
      return;
    }
    $("ul.list-group").show();
    $("ul.list-group").find("li.list-group-item").each(function (index) {
      if (response.data[index]==undefined || response.data[index]==null) {
        $(this).hide();
      } else {
        $(this).show();
        initQuestionItem($(this), response.data[index]);
      }
    });
    initPageButton(response.count, page);
  }, "json");
}

function initQuestionItem($li, data) {
  $li.find("div.assessment").find("div.sub-title").text("#"+data.id+"："+data.title);
  $li.find("div.assessment").find("div.sub-header").text(data['remarks']);
  let $answer = $li.find("div.assessment").find("div.answer");
  $answer.find("div.a").html("<label>("+data.resultA+"')&nbsp;&nbsp;A</label>&nbsp;&nbsp;"+data.contentA);
  $answer.find("div.b").html("<label>("+data.resultB+"')&nbsp;&nbsp;B</label>&nbsp;&nbsp;"+data.contentB);
  if (data.resultC!=null && data.resultC!="null" || data.resultC!=undefined) {
    $answer.find("div.c").show();
    $answer.find("div.c").html("<label>("+data.resultC+"')&nbsp;&nbsp;C</label>&nbsp;&nbsp;"+data.contentC);
  } else {
    $answer.find("div.c").hide();
  }
  if (data.resultD!=null && data.resultD!="null" || data.resultD!=undefined) {
    $answer.find("div.d").show();
    $answer.find("div.d").html("<label>("+data.resultD+"')&nbsp;&nbsp;D</label>&nbsp;&nbsp;"+data.contentD);
  } else {
    $answer.find("div.d").hide();
  }
  $answer.find("div.btn").find("button").attr("id", data.id);
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