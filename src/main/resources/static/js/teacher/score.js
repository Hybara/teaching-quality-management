$(function () {

  $(".board .panel").css({
    "cursor": "pointer"
  }).on("click", function () {
    debugger;
    var token = $("body").attr("data-token");
    var title = $(this).find("h4").text();
    var url = '';
    // console.log(title);
    var id = $("h1.page-header").attr("data-id");
    if (title == '评价') {
      url = '/teacher/goMyEvaluate/'+id+"/"+token;
    } else if (title == '答疑') {
      url = '/teacher/goMyQuestions/'+id+"/"+token;
    } else {
      url = 'assessment.html';
    }
    window.location.href = url;
  })

  $("form").on("submit", function (event) {
    event.stopPropagation();
    event.preventDefault();
    var text = $("textarea").val();
    var token = $("body").attr("data-token");
    var scoreId = $("h1.page-header").attr("data-id");
    $.post("/teacher/setRemarks", {
      token: token,
      id: scoreId,
      remarks: text
    }, function (response) {
      if (response == "ok") {
        alert("修改成功");
      } else if (response == "failure") {
        alert("修改失败");
        window.location.href = "/teacher/goScore/"+scoreId+"/"+token;
      } else if (response == "logout") {
        window.location.href = "/logout/"+token;
      }
    }, "text");
    return false;
  });

});

const NO_DATA = 0;

$(function () {
  var ctx, data, myBarChart, option_bars;
  Chart.defaults.global.responsive = true;
  option_bars = {
    scaleBeginAtZero: true,
    scaleShowGridLines: true,
    scaleGridLineColor: "rgba(0,0,0,.05)",
    scaleGridLineWidth: 1,
    scaleShowHorizontalLines: true,
    scaleShowVerticalLines: false,
    barShowStroke: false,
    barStrokeWidth: 0,
    barValueSpacing: 5,
    barDatasetSpacing: 1,
    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
  };
  var result = isNaN($(".chartJs").attr("data-result")) ? NO_DATA : $(".chartJs").attr("data-result");
  var evaluate = $("#evaluate").text();
  var question = $("#question").text();
  var assessment = $("#assessment").text();
  data = {
    labels: ['评价', '问答', '成绩', '问卷'],
    datasets: [{
      label: "My First dataset",
      fillColor: "rgba(26, 188, 156,0.2)",
      strokeColor: "#1ABC9C",
      pointColor: "#1ABC9C",
      pointStrokeColor: "#fff",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "#1ABC9C",
      data: [evaluate, question, result, assessment]
    }]
  };

  ctx = $('#radar-chart').get(0).getContext('2d');
  myBarChart = new Chart(ctx).Radar(data, option_bars);
});