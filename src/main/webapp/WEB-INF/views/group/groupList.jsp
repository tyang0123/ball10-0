<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <title>그룹 메세지</title>
</head>
<body>

<button id ="modal_click"> 그룹메세지 </button>

    <%--모달시작--%>
    <div class="modal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">그룹 메세지</h3>
                    <button id="modal_close" class="btn-close">닫기</button>
                </div>
                <div class="modal-body">
                    <c:forEach var="list" items="${list}">
                        <p>
                                ${list.user_nickname}
                        <div class="p-2 bg-light border">
                                ${list.group_message_content}
                                ${list.group_message_reg_date}
                        </div>
                        </p>
                    </c:forEach>
                    <form id = "operForm" action="/group/ajax/new" method="post">
                        <div class = "md-3">
                            <label for = "message-text" class="col-form-label"> 입력창 </label>
                            <textarea class="form-control" id="message-text"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="message_submit" class="btn btn-primary">전송</button>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript">
    $(document).ready(function (){

        var operForm = $("#operForm");

        $('#modal_click').click(function (){
            $('.modal').modal("show")
        })
        $('#modal_close').click(function (){
            $('.modal').modal("hide")
        })
        $("#message_submit").click(function (){
            operForm.attributes("action","/group/ajax/new").submit();
        });

        console.log("=============")
        console.log("JS TEST")

        messageService.add(
            {}
        )

    })
</script>



</html>




<%@ include file="../includes/footer.jsp" %>