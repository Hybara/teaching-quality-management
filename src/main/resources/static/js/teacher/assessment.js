$(function() {
	var ctx, data, myLineChart, options;
	Chart.defaults.global.responsive = true;
	options = {
		bezierCurve: true,
		bezierCurveTension: 0.4,
		pointDot: true,
		pointDotRadius: 2,
        datasetFill: false,
		multiTooltipTemplate: "<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>"
	};
	data = {
		labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
		datasets: [{
			label: "总分",
			strokeColor: "#1ABC9C",
			pointColor: "#1ABC9C",
			data: [68, 66, 70, 73, 72, 75]
		}, {
			label: "class-1",
			strokeColor: "red",
			pointColor: "red",
			data: [72, 75, 65, 65, 65, 75]
		}, {
			label: "class-2",
			strokeColor: "lightgrey",
			pointColor: "yellow",
			data: [70, 62, 62, 70 ,75, 75]
		}, {
			label: "class-3",
			strokeColor: "orange",
			pointColor: "orange",
			data: [55, 65, 63, 65, 72, 75]
		}]
	};
	ctx = $('#line-chart').get(0).getContext('2d');
	myLineChart = new Chart(ctx).Line(data, options);
	ctx = $('#line-chart-1').get(0).getContext('2d');
	myLineChart = new Chart(ctx).Line(data, options);
	ctx = $('#line-chart-2').get(0).getContext('2d');
	myLineChart = new Chart(ctx).Line(data, options);
})


$(function() {
  var ctx, data, myBarChart, option_bars;
  Chart.defaults.global.responsive = true;
  ctx = $('#bar-chart').get(0).getContext('2d');
  option_bars = {
    scaleBeginAtZero: false,
    barStrokeWidth: 1,
    barDatasetSpacing: 3,
    barValueSpacing: 7,
	multiTooltipTemplate: "<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>"
  };
  data = {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
    datasets: [
      {
        label: "class-1",
        fillColor: "rgba(26, 188, 156, 0.6)",
        strokeColor: "#1ABC9C",
        pointColor: "#1ABC9C",
        data: [65, 59, 80, 81, 56, 55]
      }, {
        label: "class-2",
        fillColor: "rgba(17, 108, 158, 0.6)",
        strokeColor: "#116C9E",
        pointColor: "#116C9E",
        data: [78, 48, 60, 89, 86, 87]
      }, {
        label: "class-3",
        fillColor: "rgba(71, 222, 21, 0.6)",
        strokeColor: "#47DE15",
        pointColor: "#47DE15",
        data: [88, 100, 80, 100, 86, 72]
      }
    ]
  };
  myBarChart = new Chart(ctx).Bar(data, option_bars);
})