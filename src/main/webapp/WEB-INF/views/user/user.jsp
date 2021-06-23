<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>




<div class="row">
    <div class="col-sm-12">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" id="Alarm">
            Launch static backdrop modal
        </button>

        ${user_nickname} 안녕하세요
        <span id="alarm-count">${alarmCount}</span>

    </div>
</div>
<!-- 유저페이지 타이머 -->
<div class="row">
    <div class="col-12 userTimer">
        <span class="timer-hours">00</span><span>:</span>
        <span class="timer-min">00</span><span>:</span>
        <span class="timer-sec">00</span>
    </div>
</div>
<div class="row">
    <div style="text-align: center;">
        <button style="width: 150px;" type="button" class="button-timer-custom" id="time-toggle">공부시작하기</button>
        <div class="userMarker"><span>${nickName}님의 속한 그룹</span></div>
    </div>
</div>
<!-- end timer -->

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 1px solid black;height: 100px;">
                <h5 class="modal-title" id="staticBackdropLabel"style="margin-left: 30px;">알람 확인하기</h5></span>
                <button style="color: black;" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="modal_body">
                    <table cellspacing="0" class="alarmTable table table-hover">
                        <thead>
                        <tr>
                            <th style="width: 20%;">날짜</th>
                            <th style="width: 70%;">내용</th>
                            <th style="width: 10%;">확인</th>
                        </tr>
                        </thead>
                        <tbody id="dataSection">
                        </tbody>
                    </table>
                </div>
            </div>
            <div style="text-align: center; margin-bottom: 20px;">
                <button style="width: 150px;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">더보기</button>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function (){
        var userID = document.cookie
                        .split('; ')
                        .find(row => row.startsWith('userCookie'))
                        .split('=')[1];

        $("#Alarm").click(function (){
            $.ajax({
                type:"post",
                url:"/user/alarm",
                data:{
                    userID: userID
                },
                dataType:"json",
                success : function (res){
                    const list = res['list'];
                    list.forEach((i)=>console.log(i));

                    var data = "";
                    for (var i = 0; i < list.length; i++) {
                        data += "<tr class='itemTitle'>";
                        data += "<input type='hidden' value='"+list[i].alarm_message_id+"'></input>";
                        data += "<td style='font-size: 12px;' class='align-middle'>"+displayTime(list[i].alarm_message_reg_date)+"</td>";
                        data += "<td><div id='alarm-content'>" + list[i].alarm_message_content + "</div></td>";
                        data += "<td id='new'>" + list[i].alarm_message_is_new + "</td>";
                        data += "</tr><tr class='hideContent'><td colspan='3'>" + list[i].alarm_message_content + "</td></tr>";
                    }
                    $('#dataSection').html(data);
                },
                error : (log)=>{alert("실패"+log)}
            })
        });
     });
    //시간 디스플레이 변환
    const displayTime = (timeValue)=>{
        var today = new Date();
        var gap = today.getTime() - timeValue;
        var dateObj = new Date(timeValue);
        if(gap<(1000*60*60*24)){ //시분초  1milli second
            var hh =dateObj.getHours();
            var mi =dateObj.getMinutes();
            var ss =dateObj.getSeconds();
            return [ (hh>9?'':'0') +hh, ':',(mi>9?'':'0')+mi,':',(ss>9?'':'0')+ss].join('');
        }  else {//년월일
            var yy= dateObj.getFullYear();
            var mm= dateObj.getMonth() +1; //getMonth는 0부터 시작
            var dd = dateObj.getDate();
            return [ yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
        }
    };

</script>

<script type="text/javascript">
    $(document).ready(function () {
        // 알람클릭시 내용이 보이며, 읽음처리 구동
        var alarmShow = (".alarmTable .showContent");

        $("#dataSection").on("click","tr",function () {
            $($(this).find("#new")).text('0');
            var alarmID = $(this).find("input").val();
            console.log("여기 메세지 아이디 : "+$(this).find("input").val());
            $.ajax({
                type:"post",
                url:"/user/alarmCount",
                data:{
                    alarmID:alarmID,
                    userID:"${userID}"
                },
                dataType:"json",
                success : function (res){
                    const count = res['alarmCount'];
                    $('#alarm-count').text(count);
                },
                error : (log)=>{alert("실패"+log)}
            })

            //add,remove 클래스로 눌를때 테이블내용이 보인다
            var myAlarm = $(this).next("tr");
            console.log($(this));
            if ($(myAlarm).hasClass('hideContent')) {

                $(alarmShow).removeClass('showContent').addClass('hideContent');
                $(myAlarm).removeClass('hideContent').addClass('showContent');
            } else {
                $(myAlarm).addClass('hideContent').removeClass('showContent');
            }

        });
    });
</script>

<script src="/resources/js/timer.js"></script>
<script>
    $(document).ready(function () {
        var timerCookieStr = document.cookie
                      .split('; ')
                      .find(row => row.startsWith('timerCookie'))
                      .split('=')[1];

        // var timerCookieStr = "125-1-10:20:10"

        var timerPlayFlag = false;
        $("#time-toggle").click(function(e){
            if(timerPlayFlag){
                $(this).html('공부시작하기');
                timerPlayFlag = false;
                timerStop(function(resultCookieTimer){
                    //타이머정보가 db에 저장되면 타이머의 정보를 쿠키에 저장
                    document.cookie = "timerCookie="+resultCookieTimer;
                });
            }else{
                $(this).html('공부그만하기');
                timerPlayFlag = true;
                timerStart(function(resultCookieTimer){
                    //타이머정보가 db에 저장되면 타이머의 정보를 쿠키에 저장
                    document.cookie = "timerCookie="+resultCookieTimer;
                });
            }
        });//end time-toggle click

        //타이머 셋팅
        timerNumberInit($(".userTimer"), $("#time-toggle"), timerCookieStr);
    });
</script>


<%@ include file="../includes/footer.jsp" %>