$(function () {
  $("input#endTime").on("blur", function () {
    let endTime = new Date($(this).val());
    if (endTime == "Invalid Date") {
      $("span.message-endTime").text("请输入正确的时间格式").addClass(
          "text-danger").removeClass("text-success");
      $(this).focus();
      $("button.submit").attr("disabled", true);
      return;
    }
    if (endTime.getTime() < (new Date()).getTime()) {
      $("span.message-endTime").text("结束时间应大于当前时间").addClass(
          "text-danger").removeClass("text-success");
      $(this).focus();
      $("button.submit").attr("disabled", true);
      return;
    }
    $("span.message-endTime").text("可使用的时间").addClass(
        "text-success").removeClass("text-danger");
    $("button.submit").attr("disabled", false);
  });

  $("button.submit").on("click", function () {
    let endTime = new Date($("input#endTime").val());
    if (endTime == "Invalid Date") {
      $("span.message-endTime").text("请输入正确的时间格式").addClass(
          "text-danger").removeClass("text-success");
      $(this).focus();
      $("button.submit").attr("disabled", true);
      return;
    }
    if (endTime.getTime() < (new Date()).getTime()) {
      $("span.message-endTime").text("结束日期应大于当前日期").addClass(
          "text-danger").removeClass("text-success");
      $(this).focus();
      $("button.submit").attr("disabled", true);
      return;
    }

    let token = $("body").attr("data-token");
    let urltype = $("div#wrapper").attr("data-url");
    let majorId = $("li.major").attr("data-id");
    let teacherId = $("li.teacher").attr("data-id");
    let scoreId = $("li.score").attr("data-id");
    $.post("/register/score/questionnaire/createQuestionnaire", {
      scoreId: scoreId,
      templateId: teacherId,
      endTime: endTime,
      token: token
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
      } else if (response == "ok") {
        alert("发布成功！");
      } else if (response == "failure") {
        alert("发布失败！");
      }
      window.location.href = "/register/goScore/" + urltype + "/" + majorId
          + "/" + teacherId + "/" + scoreId + "/" + token;
    }, "text");
  });
});