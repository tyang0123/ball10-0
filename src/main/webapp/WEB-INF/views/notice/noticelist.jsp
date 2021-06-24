<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

<h1>공지사항</h1>

<button id="createNoticeButton" value="${userID}">공지작성</button>
<c:forEach var="list" items="${list}">
    <div>
        <row>
            <b>작성일</b> : <fmt:formatDate pattern="yyyy.MM.dd" value="${list.notice_mod_date}"/>
        </row>
        <low class = "notice_title">
            <b class="title">제목</b>
            <button id="dropDownIcon${list.notice_id}">보기</button>
<%--            <i class="bi bi-chevron-down">아이콘</i>--%>
            <div class = "dropdown">
                <div id="noticeContent${list.notice_id}">
                        ${list.notice_content}
                    <button id = "noticeModify">수정하기</button>
                </div>
            </div>
        </low>
    </div>
</c:forEach>

<!-- modal -->
<div class="modal fade" id="createNotice" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">공지작성</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="recipient-name" placeholder="제목">
                    </div>
                    <div class="mb-3">
                        <textarea class="form-control" id="notice-text" placeholder="내용"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id ="noticeSubmit" class="btn btn-primary">등록하기</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/js/notice.js"></script>
<script>
$(document).ready(function (){

    //공지등록
    $('#createNoticeButton').click(function (){
        $('#createNotice').modal("show")

        $('#noticeSubmit').click(function (){
            var notice = {
                "notice_content": $('#notice-text').val()
            }
            noticeService.add(notice,function (result){
                if(result == "success"){
                    alert("등록되었습니다.")
                    $('#notice-text').val("");
                    $('#createNotice').modal("hide");
                }
            })
        })
    })

    //공지 수정하기
    $('#noticeModify').click(function (){
        console.log("수정하기")
    })

    //공지 읽기
    var notice_count = ${noticeCount};
    $("[id^='noticeContent']").hide()
    for(i=1; i<=notice_count; i++){
        (function (m){
            document.getElementById("dropDownIcon"+m).addEventListener("click",function (){
                $('#noticeContent'+m).show()
            },false);
        })(i);
    }

    //공지등록 admin 에게만 보이게
    $('#createNoticeButton').hide()
    let userID = $('#createNoticeButton').val()
    if(userID == 'admin'){
        $('#createNoticeButton').show()
    }else console.log("admin 계정이 아님")
    })

</script>

<%@ include file="../includes/footer.jsp" %>
