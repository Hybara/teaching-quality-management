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

  initScoresList();

});

function initScoresList() {
  $.ajax({
    url: "",
    type: "post",
    method: "post",
    dataType: "json",
    data: "",
    success: function (response) {
      var ctx, data, myBarChart;
      Chart.defaults.global.responsive = true;
      data = {
        labels: ['评价', '问答', '平均分', '问卷'],
        datasets: [{
          label: "My First dataset",
          fillColor: "rgba(26, 188, 156,0.2)",
          strokeColor: "#1ABC9C",
          pointColor: "#1ABC9C",
          pointStrokeColor: "#fff",
          pointHighlightFill: "#fff",
          pointHighlightStroke: "#1ABC9C",
          data: [65, 59, 80, 81]
        }]
      };
      var canvasParent = $('#radar-chart').parents("div.col-md-8");
      $('#radar-chart').css({
        "width": canvasParent.css("width"),
        "height": canvasParent.css("height")
      });
      ctx = $('#radar-chart').get(0).getContext('2d');
      myBarChart = new Chart(ctx).Radar(data, option_bars);
    }
  })
}