$(function () {

  /*修改个人头像弹框上传照片按钮事件*/
  if(typeof FileReader === "undefined") {
    $("input[type=file]").attr("disabled", "disabled");
  } else {
    $("#edit_photo_form .file input").change(function() {
      var file = $(this).get(0).files[0];//读取文件
      //FileReader，用于把文件读入内存，并读取文件中的数据
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function(e) {
        $("#edit_photo_form .photo_border dt img").attr("src", this.result);
        $("a.file img").attr("src", this.result);
      }

    });
  }

  $.post("/student/getHeader",
      {filename: $("#stu_header").attr("data-src")},
      function (response) {
        // console.log("response", response);
        $("#stu_header").attr("src", response);
  }, "json");

});