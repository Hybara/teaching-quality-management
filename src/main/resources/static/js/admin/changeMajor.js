$(function () {

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();

    let id = $("h1.page-header").attr("data-id");

    let number = $("input#number").val();
    let name = $("input#name").val();
    let schooling = $("input#schooling").val();
    let token = $("body").attr("data-token");

    $.post("/admin/changeMajor", {
      id: id,
      number: number,
      name: name,
      schooling: schooling,
      token: token
    }, function (response) {
      if (response == "logout") {
        window.location.href = "/logout/"+token;
      } else {
        window.location.href = "/admin/login/"+token;
      }
    }, "text");

  });
});