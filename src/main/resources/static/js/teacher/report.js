$(function () {

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();

    let reason = $("input").val();
    let reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/g;
    if (!reg.test(reason)) {
      $("input").focus();
      $("span.message").text("请输入汉字、字母或数字").addClass("text-danger").removeClass(
          "text-success");
      return ;
    }
    let type = $("h1.page-header").attr("data-id");
    let id = $("li.report.active").attr("data-id");
    let token = $("body").attr("data-token");

    $.post("/teacher/addReport", {
      type: type,
      id: id,
      token: token,
      reason: reason
    }, function (response) {
      if (response == "logout") {
        alert("登录超时，请重新登录！");
      } else if (response == "ok") {
        alert("举报成功！即将关闭本页面");
      } else {
        alert("举报失败！即将关闭本页面");
      }
      window.opener=null;
      window.open('','_self');
      window.close();
    }, "text");

  });

});