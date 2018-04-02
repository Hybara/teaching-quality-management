const options = {
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

const PAGE_ONE = 1;
const NO_DATA = 0;

var type = "all";
var page_count = 0;
var search = "";

$(function () {

  initScoresList(type, PAGE_ONE, search);

  $(".pagination a").on("click", function() {
    var page = $(this).text();
    if (!isNaN(page)) {
    } else if ("«"==page) {
      var now = $("ul.pagination li.active a").text();
      page = (now>1)?(now-1):1;
    } else {
      var now = $("ul.pagination li.active a").text();
      page = (now==page_count)?page_count:(parseInt(now)+1);
    }
    initScoresList(type, page, search);
  });

  $("ul.nav-second-level a").on("click", function () {
    search = "";
    type = $(this).attr("data-score-type");
    initScoresList(type, PAGE_ONE, search);
  });

  $("#search_form").on("submit", function () {
    search = $("#search").val();
    $("#search").val("");
    initScoresList(type, PAGE_ONE, search);
    return false;
  });

});

function initScoresList(type, page, search) {
  var token = $("body").attr("data-token");
  var data;
  if (search=="") {
    data = {page: page};
  } else {
    data = {page: page, search: search};
  }
  $.ajax({
    url: "/student/getScores/"+type+"/"+token,
    type: "post",
    method: "post",
    dataType: "json",
    data: data,
    success: function (scoreList) {
      var ctx, myBarChart;
      Chart.defaults.global.responsive = true;
      // console.log("scoreList", scoreList);
      page_count = scoreList.count;
      initPageButton(page_count, page);
      for(var i=0; i<$(".scoreItem").length; i++) {
        $item = $($(".scoreItem").get(i));
        if (scoreList.data[i]==undefined || scoreList.data[i]==null) {
          $item.css("display", "none");
        } else {
          $item.css("display", "block");
          initScore($item, scoreList.data[i]);
          var scoreItem = scoreList.data[i];
          var evaluate = null;
          if (scoreItem.evaluateCount!=0) {
            evaluate = scoreItem.evaluateGrade/scoreItem.evaluateCount;
          }
          var question = null;
          if (scoreItem.questionCount!=0) {
            question = scoreItem.questionGrade/scoreItem.questionCount;
          }
          var assessment = null;
          if (scoreItem.assessmentCount!=0) {
            assessment = scoreItem.assessmentGrade/scoreItem.assessmentCount;
          }
          var data = initRaderData(scoreItem.scoreName, evaluate, question, assessment, scoreItem.result);
          initRader($item, data);
        }
      }
    }
  })
}

function initScore($item, score) {
  var token = $("body").attr("data-token");
  // console.log("a href", "/getScore/"+score.id+"/"+token);
  $item.find("h4").find("a").attr("href", "/student/goScore/"+score.id+"/"+token).text(score.scoreName);
  $item.find("h6").find("small").text(score.scoreTypeName);
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
        evaluate==null || isNaN(evaluate) ? NO_DATA : evaluate,
        question==null || isNaN(question) ? NO_DATA : question,
        result==null || isNaN(result) ? NO_DATA : result,
        assessment==null || isNaN(assessment) ? NO_DATA : assessment]
    }]
  };
  return data;
}

function initRader($item, data) {

  $item.find(".chart").remove();
  $item.find("center").append("<canvas class='chart'></canvas>");
  $item.find(".chart").css({
    "width": $(".scoreItem").css("width"),
    "height": $(".scoreItem").css("height")
  });
  var ctx = $item.find(".chart").get(0).getContext('2d');
  new Chart(ctx).Radar(data, options);
}

function initPageButton(count, page) {
  if (count==0) {
    $("ul.pagination li").hide();
    return;
  }
  $("ul.pagination li").show();
  $("ul.pagination li.pageItem").hide();
  // console.log("count", count);
  for(var i=0; i<count; i++) {
    $($("ul.pagination li.pageItem")[i]).show().removeClass("active");
  }
  $($("ul.pagination li").get(page)).addClass("active");
}