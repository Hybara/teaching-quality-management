$(function () {
  $("form").on("submit", function () {
    let token = $("body").attr("data-token");
    let questionId = $(this).attr("data-id");
    let title = $("textarea#title").val();
    let remarks = $("textarea#remarks").val();
    let content_a = $("#content_a").val();
    let result_a = $("#result_a").val();
    let content_b = $("#content_b").val();
    let result_b = $("#result_b").val();
    let content_c = $("#content_c").val();
    let result_c = $("#result_c").val();
    let content_d = $("#content_d").val();
    let result_d = $("#result_d").val();
    $.post("/register/questionnaire/addQuestion", {
      type: $("h1.page-header").attr("data-id"),
      token: token,
      id: questionId,
      title: title,
      remarks: remarks,
      contentA: content_a,
      resultA: result_a,
      contentB: content_b,
      resultB: result_b,
      contentC: content_c,
      resultC: result_c,
      contentD: content_d,
      resultD: result_d
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
        reutrn;
      } else if (response == "ok") {
        if (questionId == 0) {
          alert("添加成功！")
        } else {
          alert("修改成功！");
        }
      } else if (respnse == "failure") {
        if (questionId == 0) {
          alert("添加失败！");
        } else {
          alert("修改失败！");
        }
      }
    }, "text");
    return false;
  });

  $("#delete").on("click", function () {
    let token = $("body").attr("data-token");
    let questionId = $("form").attr("data-id");
    let type = $("h1.page-header").attr("data-id");
    $.post("/register/questionnaire/delQuestion", {
      type: type,
      token: token,
      id: questionId
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/" + token;
        reutrn;
      } else if (response == "ok") {
        alert("删除成功！");
        window.location.href = "/register/questionnaire/question/" + type + "/"
            + token;
      } else if (respnse == "failure") {
        alert("删除失败！");
      }
    }, "text");
  });
});