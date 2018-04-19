$(function () {

  $("input[type=radio]").attr("checked", false);
  $("input[type=radio]").on("click", function (event) {
    $(event.target).attr("checked", true);
    $(event.target).parent().siblings().find("input[type=radio]").attr(
        "checked", false);
  });

  $("button.submit").on("click", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let results = new Array();
    let ids = new Array();
    $("input[type=radio][checked]").each(function (index) {
      results[index] = $(this).val();
      ids[index] = $(this).attr("name");
    });

    let length = $("div.evaluate").attr("data-id");
    if (length != ids.length) {
      $("span.message").text("请确认是否所有题目都被选中").addClass("text-danger");
      return;
    }
    let questionnaireId = $("h1.page-header").attr("data-id");
    let token = $("body").attr("data-token");
    let scoreId = $("li#score").attr("data-id");
    let teacherId = $("li#teacher").attr("data-id");
    $.post("/teacher/fillInQuestionnaire", {
      questionnaireId: questionnaireId,
      scoreId: scoreId,
      results: results,
      ids: ids,
      token: token
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
      } else if (response == "failure") {
        alert("未知错误，即将返回课程页面");
      } else if (!isNaN(response)) {
        alert("本次评教的得分是： " + response + " 分！");
      }
      window.location.href = "/teacher/goScore/"+teacherId+"/"+scoreId+"/"+token;
    }, "text");

  });
});