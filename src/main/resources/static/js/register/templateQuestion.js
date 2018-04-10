$(function () {

  $("form#coefficient").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let token = $("body").attr("data-token");
    let questionIds = new Array();
    let coefficients = new Array();
    $("select[name=coefficient]").each(function (index) {
      questionIds[index] = $(this).attr("data-id");
      coefficients[index] = $(this).val();
    });
    console.log(questionIds, coefficients);
    $.post("/register/template/changeQuestionCoefficient", {
      questionIds: questionIds,
      coefficients: coefficients,
      token: token
    }, function (response) {

      if (response == "logout") {
        window.location.href = "/logout/" + token;
        return;
      } else if (response == "ok") {
        alert("修改成功");
      } else {
        alert("修改失败");
      }
      let templateId = $("h1.page-header").attr("data-id");
      window.location.href = "/register/template/goQuestionnaire/" + templateId
          + "/" + token;
    }, "text");
    return false;
  });

  $("form#template").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    let templateId = $("h1.page-header").attr("data-id");
    let token = $("body").attr("data-token");
    let name = $("input[name=name]").val();
    $.post("/register/template/changeTemplateName", {
      templateId: templateId,
      token: token,
      name: name
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
        return;
      } else if (response == "nodata") {
        alert("没有数据，修改失败！");
        $("input[name=name]").focus();
        return;
      } else if (response == "ok") {
        alert("修改成功");
      } else if (response == "failure") {
        alert("修改失败");
      }
      window.location.href = "/register/template/goQuestionnaire/" + templateId
          + "/" + token;
    }, "text");
    return false;
  });

});