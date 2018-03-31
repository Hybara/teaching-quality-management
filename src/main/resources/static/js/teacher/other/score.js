$(function () {

  $(".board .panel").css({
    "cursor": "pointer"
  }).on("click", function () {
    var token = $("body").attr("data-token");
    var teacherId = $("div.header").attr("data-id");
    var title = $(this).find("h4").text();
    var url = '';
    // console.log(title);
    var id = $("h1.page-header").attr("data-id");
    if (title == '评价') {
      url = "/teacher/goEvaluate/"+teacherId+"/"+id+"/"+token;
    } else if (title == '提问') {
      url = '/teacher/goQuestions/'+id+"/"+token;
    } else {
      url = 'assessment.html';
    }
    window.location.href = url;
  })

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