const INDEX_PAGE = 1;
const PAGINATION_PAGINTEM_NUMBER = 5;

let type;
let token = $("body").attr("data-token");
let page_count;
let questions = new Array();
let types = new Array();
let questions_index = 0;

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
    initQuestionnaireBank($("select[name=type]").val(), INDEX_PAGE);
  });

  $("button.select").on("click", function () {
    if (questions.length >= 20) {
      alert("选题上限（20道）！");
      return;
    }
    if ($(this).text() == "移除") {
      let questionId = $(this).attr("id");
      let index = jQuery.inArray(questionId, questions);
      questions.splice(index, 1);
      types.splice(index, 1);
      $(this).removeClass("btn-warning").addClass("btn-success").text("选择");
      questions_index--;
      console.log("questions", questions);
    } else {
      let questionId = $(this).attr("id");
      questions[questions_index] = questionId;
      types[questions_index++] = type;
      $(this).removeClass("btn-success").addClass("btn-warning").text("移除");
      console.log("questions", questions);
    }
    if (questions.length >= 8 && questions.length <= 20) {
      $("button.submit").attr("disabled", false);
    } else {
      $("button.submit").attr("disabled", true);
    }
    initQuestionPanel();
  });

  $("input[name=name]").on("blur", function () {
    let name = $(this).val();
    let reg = /[^\u4E00-\u9FA5]+$/g;
    if (reg.test(name)) {
      $(this).focus();
      $("span.message").text("请输入中文").addClass("text-danger").removeClass(
          "text-success");
      $("button.submit").attr("disabled", true);
    } else {
      $.post("/register/template/checkTemplateName", {
        templateName: name,
        token: token
      }, function (response) {
        if (response == "logout") {
          window.location.href = "/logout/" + token;
        } else if (response == "ok") {
          $("span.message").text("该名称可以使用").addClass(
              "text-success").removeClass("text-danger");
          $("button.submit").attr("disabled", false);
        } else if (response == "failure") {
          $("span.message").text("该名称已存在").addClass("text-danger").removeClass(
              "text-success");
          $("button.submit").attr("disabled", true);
          $("input[name=name]").focus();
        }
      }, "text");
    }

    $("button.submit").on("click", function (event) {
      event.stopPropagation();
      event.preventDefault();
      let name = $("input[name=name]").val();
      let reg = /[^\u4E00-\u9FA5]+$/g;
      if (name == "") {
        $("span.message").text("请输入模板名称").addClass("text-danger").removeClass(
            "text-success");
        $("button.submit").attr("disabled", true);
        return;
      }
      if (reg.test(name)) {
        $("input[name='name']").focus();
        $("span.message").text("请输入中文").addClass("text-danger").removeClass(
            "text-success");
        $("button.submit").attr("disabled", true);
        return;
      }
      if (questions.length == 0 || questions.length < 8) {
        $("span.btn-message").text("请选择题目，至少8道题").addClass(
            "text-danger").removeClass("text-success");
        $("button.submit").attr("disabled", true);
        return;
      } else {
        $.post("/register/template/addTemplate", {
          templateName: name,
          questionIds: questions,
          token: token
        }, function (response) {
          window.location.href = "/register/template/goTemplate/" + token;
        }, "text");
      }
    });

  });

  initQuestionnaireBank($("select[name=type]").val(), INDEX_PAGE);

});

function initQuestionPanel() {
  for (let i = 0; i < questions.length; i++) {
    $("div#" + types[i]).text("").addClass("text-info");
  }
  for (let i = 0; i < questions.length; i++) {
    $("div#" + types[i]).text($("div#" + types[i]).text() + " #"
        + questions[i]);
  }
}

function initQuestionnaireBank(typeId, page) {
  $.post("/register/getQuestionBank", {
    type: typeId,
    token: token,
    page: page
  }, function (response) {
    console.log("response", response);
    type = typeId;
    page_count = response.count;
    $("div.question ul.list-group").hide();
    $("div.question ul.pagination").hide();
    if (response.data == undefined || response.data == null || response.count
        == 0) {
      return;
    }
    $("div.question ul.list-group").show();
    $("div.question ul.list-group").find("li.list-group-item").each(
        function (index) {
          if (response.data[index] == undefined || response.data[index]
              == null) {
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
  $li.find("div.answers").find("div.sub-title").text("#" + data.id + "："
      + data.title);
  $li.find("div.answers").find("div.sub-header").text(data['remarks']);
  let $answer = $li.find("div.answers").find("div.answer");
  $answer.find("div.a").html("<label>(" + data.resultA
      + "')&nbsp;&nbsp;A</label>&nbsp;&nbsp;" + data.contentA);
  $answer.find("div.b").html("<label>(" + data.resultB
      + "')&nbsp;&nbsp;B</label>&nbsp;&nbsp;" + data.contentB);
  if (data.resultC != null && data.resultC != "null" || data.resultC
      != undefined) {
    $answer.find("div.c").show();
    $answer.find("div.c").html("<label>(" + data.resultC
        + "')&nbsp;&nbsp;C</label>&nbsp;&nbsp;" + data.contentC);
  } else {
    $answer.find("div.c").hide();
  }
  if (data.resultD != null && data.resultD != "null" || data.resultD
      != undefined) {
    $answer.find("div.d").show();
    $answer.find("div.d").html("<label>(" + data.resultD
        + "')&nbsp;&nbsp;D</label>&nbsp;&nbsp;" + data.contentD);
  } else {
    $answer.find("div.d").hide();
  }

  let $btn = $answer.find("div.btn").find("button");
  $btn.attr("id", data.id);

  let flag = true;
  debugger;
  for (let i = 0; i < questions.length; i++) {
    if (questions[i] == data.id) {
      $btn.removeClass("btn-success").addClass("btn-warning").text("移除");
      flag = false;
      break;
    }
  }
  if (flag) {
    $btn.removeClass("btn-warning").addClass("btn-success").text("选择");
  }
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