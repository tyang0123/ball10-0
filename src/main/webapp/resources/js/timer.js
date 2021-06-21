var hour;
var minute;
var second;

var hourScreen;
var minScreen;
var secScreen;

var timerIntervalID;

// 초기 타이머 세팅
function timerNumberInit(myTimer, timerStr) {
  var timeArray = timerStr.split(':');

  hour = Number(timeArray[0]);
  minute = Number(timeArray[1]);
  second = Number(timeArray[2]);

  hourScreen = $(myTimer).find('.timer-hours');
  minScreen = $(myTimer).find('.timer-min');
  secScreen = $(myTimer).find('.timer-sec');

  hourScreen.html((hour < 10 ? '0' : '') + hour);
  minScreen.html((minute < 10 ? '0' : '') + minute);
  secScreen.html((second < 10 ? '0' : '') + second);
}

var timerStart = function() {
  timerIntervalID = setInterval(function () {
    second = second + 1;
    if (second >= 60) {
      minute = minute + 1;
      second = 0;
    }
    if (minute >= 60) {
      hour = hour + 1;
      minute = 0;
    }
    hourScreen.html((hour < 10 ? '0' : '') + hour);
    minScreen.html((minute < 10 ? '0' : '') + minute);
    secScreen.html((second < 10 ? '0' : '') + second);
  }, 1000);
}
var timerStop = function (timerID, resultFunc) {
  clearInterval(timerIntervalID);
  //ajax로 db에 저장해야함

  var accumulatedTime = (hour < 10 ? '0' : '') + hour + ":" 
      + (minute < 10 ? '0' : '') + minute +":"+(second < 10 ? '0' : '')+ second

  console.log("test")
  $.ajax({
    type: "PUT",
    url: "/ajax/timer/put/"+timerID,
    data : accumulatedTime,
    contentType: "application/json; charset=UTF-8;",
    success: function(result, status, xhr){
      resultFunc(accumulatedTime)
    },
    error: function(xhr, status, er){
      alert("error : "+er)
    }
  })
};
