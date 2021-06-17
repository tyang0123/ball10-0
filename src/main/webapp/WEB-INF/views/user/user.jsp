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

    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 1px solid black;">
                <h5 class="modal-title" id="staticBackdropLabel">알람 확인하기</h5></span>
                <button style="color: black;" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="modal_body">
                    <table class="table table-hover">
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
                url:"/ajax/user/alarm",
                data:{
                    user: userID
                },
                dataType:"json",
                success : function (res){
                    const list = res['list'];
                    list.forEach((i)=>console.log(i));

                    var data = "";
                    for (var i = 0; i < list.length; i++) {
                        data += "<tr>";
                        data += "<td style='font-size: 12px;' class='align-middle'>"+displayTime(list[i].alarm_message_mod_date)+"</td>";
                        data += "<td><div style='white-space: nowrap; overflow: hidden;text-overflow: ellipsis; width:450px;'>" + list[i].alarm_message_content + "</div></td>";
                        data += "<td>" + list[i].alarm_message_is_new + "</td>";
                        data += "</tr>";
                    }
                    $('#dataSection').html(data);
                    $('#staticBackdrop').modal("show");
                },
                error : (log)=>{alert("실패"+log)}
            })
        });
    });
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
    }
</script>



<%@ include file="../includes/footer.jsp" %>