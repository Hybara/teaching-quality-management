var options = {
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

$(function() {

  initScoresList(1);

});

function initScoresList(page) {
  var token = $("body").attr("data-token")
  // console.log("token: ", token)
  $.ajax({
    url: "/student/getScores/all/"+token+"?page="+page,
    type: "post",
    method: "post",
    dataType: "json",
    data: "",
    success: function (scoreList) {
      var ctx, myBarChart;
      Chart.defaults.global.responsive = true;
      for(var i=0; i<9; i++) {
        $item = $($(".scoreItem").get(i));
        if (scoreList[i]==undefined || scoreList[i]==null) {
          $item.css("display", "none");
        } else {
          $item.css("display", "block");
          initScore($item, scoreList[i]);
          var evaluate = scoreList[i].evaluateGrade/scoreList[i].evaluateCount;
          var question = scoreList[i].questionGrade/scoreList[i].questionCount;
          var assessment = scoreList[i].assessmentGrade/scoreList[i].assessmentCount;
          var data = initRaderData(scoreList[i].scoreName, evaluate, question, assessment, scoreList[i].result);
          initRader($item, data);
        }
      }
    }
  })
}

function initScore($item, score) {
  $item.find("h4").find("a").attr("href", "#").text(score.scoreName);
  $item.find("h6").find("small").text(score.scoreType);
  $item.find("h5").html(score.teacherName+"<br/><small>"+score.teacherBusiness+"</small>");
}

function initRaderData(scoreName, evaluate, question, assessment, result) {
  var data = {
    labels: ['评价', '问答', '平均分', '问卷'],
    datasets: [{
      label: scoreName,
      fillColor: "rgba(26, 188, 156,0.2)",
      strokeColor: "#1ABC9C",
      pointColor: "#1ABC9C",
      pointStrokeColor: "#fff",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "#1ABC9C",
      data: [
        isNaN(evaluate) ? 0 : evaluate,
        isNaN(question) ? 0 : question,
        result==null || isNaN(result) ? 0 : result,
        isNaN(assessment) ? 0 : assessment]
    }]
  };
  return data;
}

function initRader($item, data) {
  var canvasParent = $('#radar-chart').parents("div.col-md-8");
  $('#radar-chart').css({
    "width": canvasParent.css("width"),
    "height": canvasParent.css("height")
  });
  // console.log($item)
  var ctx = $item.find(".chart").get(0).getContext('2d');
  var myBarChart = new Chart(ctx).Radar(data, options);
}