<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<!-- 유저페이지 알람모달 -->
<div class="row">
    <div class="col-2"></div>
    <div class="col-8 userMarker"><span>${nickName}님의 오늘의 공부량</span></div>
    <div style="margin-top: 30px; position: relative;" class="col-2">
        <a data-bs-toggle="modal" href="#staticBackdrop" id="Alarm">
            <img src="/resources/img/letter.png" id="letter-img" /><span id="alarm-count" class="badge rounded-pill bg-warning text-dark">${alarmCount}</span>
        </a>
    </div>
</div>

<!-- 유저페이지 타이머 -->
<div class="row">
    <div class="col-12 userTimer">
        <span class="timer-hours">00</span><span>:</span><span class="timer-min">00</span><span>:</span><span class="timer-sec">00</span>
    </div>
</div>
<div class="row">
    <div style="text-align: center;">
        <button style="width: 150px;" type="button" class="button-timer-custom" id="time-toggle">공부시작하기</button>
        <div class="userMarker"><span>${nickName}님의 속한 그룹</span></div>
    </div>
</div>
<!-- end timer -->

<!-- 유저페이지 그룹리스트 -->
<div class="row">
    <div style="background-color: #efefef; margin-top: 20px; padding-top:20px; padding-bottom: 40px;" class="center-block;">
        <c:if test="${empty userJoinGroupList}">
            <div style="text-align: center; margin-top: 20px;">
            <img src='/resources/img/group_empty.jpg' class="group-empty"/>
            </div>
        </c:if>
        <c:forEach var="groupList" items="${userJoinGroupList}">
            <div class="card user-card-group">
                <div class="card-body">
                    <div class="row">
                        <div class="col-10 group-category">${groupList.group_category}</div>
                        <div class="col-2 text-end groupSecret">
                            <c:if test="${groupList.group_is_secret==1}">
                                <img src='/resources/img/lock.png' id='lockImg'/>
                            </c:if>
                        </div>
                    </div>
                    <div class="group-list-margin">
                        <span class="group-title">${groupList.group_name}</span>
                    </div>
                    <div>
                        <span class="group-list-title">목표시간 : </span><span class="group-list-content">7시간</span><span class="group-list-title"> 그룹인원 : </span><span class="group-list-content">${groupList.group_join_person_number}/${groupList.group_person_count}명</span><span class="group-list-title">  그룹장 : </span><span class="group-list-content">${groupList.user_nickname_group_header}</span>
                    </div>
                    <div>
                        <span class="group-list-title">공부량 : </span><span class="group-list-content">6시간 50분</span>
                        <span class="group-list-title">  시작일 : </span><span class="group-list-content">
                        <fmt:parseDate var="date" value="${groupList.group_reg_date}" pattern="yyyy-MM-dd"/>
                            <fmt:formatDate value="${date}" type="DATE" pattern="yyyy-MM-dd"/></span>
                    </div>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item group-content">${groupList.group_content}</li>
                </ul>
            </div>
        </c:forEach>

    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 1px solid black;height: 100px;">
                <h4 class="modal-title" id="staticBackdropLabel"style="margin-left: 30px;">알람 확인하기</h4></span>
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
            <div style="text-align: center; margin-bottom:20px; margin-top: 20px;">
                <button style="width: 150px;" type="button" class="button-add-custom" id="addBtn">더보기</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var changeCriterionNumber=${firstCriterionNumber};
    $(document).ready(function (){

        //알람메세지 모달
        $("#Alarm").click(function (){

            //페이지 로딩시 첫 실행
            moreList(changeCriterionNumber);

            //더보기 버튼 클릭시
           $("#addBtn").click(function (){
               moreList(changeCriterionNumber);
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
    //읽음부분 이미지처리
    const changeImg = (newCheck)=>{
        if(newCheck==1){
            return "<svg style='color:#ff9000' xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-envelope-fill' viewBox='0 0 16 16'><path d='M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555zM0 4.697v7.104l5.803-3.558L0 4.697zM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757zm3.436-.586L16 11.801V4.697l-5.803 3.546z'/></svg>";
        }else {
            return "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-envelope-open' viewBox='0 0 16 16'><path d='M8.47 1.318a1 1 0 0 0-.94 0l-6 3.2A1 1 0 0 0 1 5.4v.818l5.724 3.465L8 8.917l1.276.766L15 6.218V5.4a1 1 0 0 0-.53-.882l-6-3.2zM15 7.388l-4.754 2.877L15 13.117v-5.73zm-.035 6.874L8 10.083l-6.965 4.18A1 1 0 0 0 2 15h12a1 1 0 0 0 .965-.738zM1 13.117l4.754-2.852L1 7.387v5.73zM7.059.435a2 2 0 0 1 1.882 0l6 3.2A2 2 0 0 1 16 5.4V14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V5.4a2 2 0 0 1 1.059-1.765l6-3.2z'/></svg>";
        }
    }
    //리스트가 추가되는 함수
    const moreList = (criterionNumber)=>{
        $.ajax({
            type:"post",
            url:"/user/alarmMessage",
            data:{
                userID:"${userID}",
                criterionNumber:criterionNumber
            },
            dataType:"json",
            success : function (res){
                const list = res['list'];
                var data = "";

                for (var i = 0; i < list.length; i++) {
                    data += "<tr class='itemTitle'>";
                    data += "<input type='hidden' value='" + list[i].alarm_message_id + "'></input>";
                    data += "<td style='font-size: 12px;' class='align-middle'>" + displayTime(list[i].alarm_message_reg_date) + "</td>";
                    data += "<td><div id='alarm-content'>" + list[i].alarm_message_content + "</div></td>";
                    data += "<td id='new' style='padding-left: 15px;'>" + changeImg(list[i].alarm_message_is_new) + "</td>";
                    data += "</tr><tr class='hideContent' style='pointer-events: none;'><td></td><td>" + list[i].alarm_message_content + "</td><td></td></tr>";
                }
                $('#dataSection').append(data);
                changeCriterionNumber = $("#dataSection input:last").val();

                //더보기할 내용없을시 더보기 버튼 삭제
                if(list.length<10){
                    $("#addBtn").remove();
                }
            },
            error : ()=>{}
        });
    };

</script>

<script type="text/javascript">
    $(document).ready(function () {
        // 알람클릭시 내용이 보이며, 읽음처리 구동
        var alarmShow = (".alarmTable .showContent");

        $("#dataSection").on("click","tr",function () {
            $($(this).find("#new")).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-envelope-open' viewBox='0 0 16 16'><path d='M8.47 1.318a1 1 0 0 0-.94 0l-6 3.2A1 1 0 0 0 1 5.4v.818l5.724 3.465L8 8.917l1.276.766L15 6.218V5.4a1 1 0 0 0-.53-.882l-6-3.2zM15 7.388l-4.754 2.877L15 13.117v-5.73zm-.035 6.874L8 10.083l-6.965 4.18A1 1 0 0 0 2 15h12a1 1 0 0 0 .965-.738zM1 13.117l4.754-2.852L1 7.387v5.73zM7.059.435a2 2 0 0 1 1.882 0l6 3.2A2 2 0 0 1 16 5.4V14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V5.4a2 2 0 0 1 1.059-1.765l6-3.2z'/></svg>");
            var alarmID = $(this).find("input").val();
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
                error : ()=>{}
            })

            //add,remove 클래스로 누를때 테이블내용이 보인다
            var myAlarm = $(this).next("tr");
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