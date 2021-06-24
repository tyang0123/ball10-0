<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../includes/header.jsp" %>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"> 그룹 조회 페이지 </div> <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="form-group">
                    <label for="group_id">방번호</label>
                    <input class="form-control" name="group_id" id="group_id" value="${group.group_id}"
                           readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="group_name"> 방제목</label>
                    <input class="form-control" name="group_name" id="group_name" value="${group.group_name}"
                           readonly="readonly">
                </div>
                <div>
                    <label for="group_category">그룹 카테고리</label>
                    <input class="form-control" name="group_category" id="group_category" value="${group.group_category}"
                           readonly="readonly">
                </div>


                <div class="form-group">
                    <label for="group_content">텍스트 영역</label>
                    <textarea class="form-control" rows="3" name="group_content" id="group_content"
                              readonly="readonly">${group.group_content}</textarea>
                </div>
                <div class="form-group">
                    <label for="user_id_group_header">방장</label>
                    <input class="form-control" name="user_id_group_header" id="user_id_group_header" value="${group.user_id_group_header}"
                           readonly="readonly">
                </div>
                <!--  264페이지 수정  -->
                <button data-oper='modify' class="btn btn-default"> 수정</button>
                <button data-oper='list' class="btn btn-info">목록 </button>
                <form id='operForm' action="/group/modify" method="get">
                    <input type="hidden" name="group_id" value="${group.group_id}" />
<%--                    <input type="hidden" name="pageNum" value="${cri.pageNum}" />--%>
<%--                    <input type="hidden" name="amount" value="${cri.amount}" />--%>
<%--                    <input type='hidden' name='type' value='<c:out value ="${cri.type}"/>'>--%>
<%--                    <input type='hidden' name='keyword' value='<c:out value ="${cri.keyword}"/>'>--%>
                </form>

                <button id="modalShowButton">그룹메세지</button>
                <%--모달시작--%>
                <div class="modal" tabindex="-1">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">그룹 메세지</h3>
                                <button id="modal_close" class="btn-close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="readGroupMessage"></div>
                                <form id = 'sendGroupMessage' action='/group/ajax/new' method='post'>
                                    <div class = 'md-3'>
                                        <label for = 'message-text' class='col-form-label'> 입력창 </label>
                                        <textarea class='form-control' id='message-text'></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" id="message_submit" class="btn btn-primary">전송</button>
                            </div>
                        </div>
                    </div>
                </div>


            </div> <!-- end panel-body -->
        </div> <!-- end panel -->
    </div> <!-- col-lg-12 -->
</div> <!-- row -->
<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript" src="/resources/js/message.js"></script>
<script>
    $(document).ready(function (){
        $(".btn-default").click(function (){
            $(operForm).attr("action","/group/modify").submit();
        })
        $(".btn-info").click(function (){
            $(operForm).find("#group_id").remove();
            $(operForm).attr("action", "/group/list").submit(); //리스트로 돌아가기
        })

        var group_id = '${group.group_id}'
        var criterionNumber = ${firstCriNumber};
        //스크롤로 더보기 구현
        var isLoading = false;

        //modal창 보여주기
        $("#modalShowButton").click(function (){
            $('.modal').modal("show")
            //첫번째 10개 메세지
            criterionNumber = criterionNumber-10;
            console.log(criterionNumber)

            messageService.getList(group_id,criterionNumber,function(result){
                $('.readGroupMessage').html(result);
                    //스크롤 이벤트
                    function loadNewPage(){
                        //다음 10개 추가
                        criterionNumber = criterionNumber-10;
                        console.log("스크롤 했을때 크리넘버: "+criterionNumber)

                        var temp = $(document).height();
                        messageService.getList(group_id,criterionNumber,function (result){
                            $('.readGroupMessage').html(result);
                            if(criterionNumber < 0) alert("마지막 메세지입니다.")

                            $(document).scrollTop($(document).height()-temp);
                            isLoading = false;
                        })
                    }

                    $(document).scroll(function (){
                        if($(document).scrollTop() <60 && !isLoading){
                            isLoading = true;
                            setTimeout(loadNewPage,1200);
                        }
                    })

                $(".remove_message").click(function (){
                    var group_message_id = $(this).val()
                    messageService.remove(group_message_id,function (deleteResult){
                        if(deleteResult == "success"){
                            alert("삭제되었습니다.");
                            $('.modal').modal("hide");
                        }
                    }, function (err){
                        alert("에러 발생");
                    })
                })

                $("#message_submit").click(function (){
                    var message = {
                        "user_id":'user1', //이후 쿠키에서 가져온 뒤 수정
                        "group_message_content": $('#message-text').val()
                    }
                    messageService.add(group_id,message,function (result){
                        if(result == "success"){
                            $('#message-text').val("");
                        }
                    })
                })
            });
        })

        $("#modal_close").click(function (){
            $('.modal').modal("hide")
        })
    })
</script>

