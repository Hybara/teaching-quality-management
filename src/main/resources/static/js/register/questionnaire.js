$(function () {

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    debugger;
    let name = $("#type").val();
    let reg = /[^\u4E00-\u9FA5]+$/g;
    if (reg.test(name)){
      alert("请输入中文！");
      $("#type").focus();
      return ;
    }
    $.post("/register/addQuestionnaireQuestionType", {
      token: $("body").attr("data-token"),
      data: name
    }, function (response) {
      console.log("response", response);
    }, "text");
  });

});