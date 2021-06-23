<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp" %>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"> 그룹 조회 페이지 </div> <!-- /.panel-heading -->
            <div class="panel-body">
                <form id='operForm' action="/group/read">
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
                    <label for="user_nickname_group_header">그룹장</label>
                    <input class="form-control" name="user_nickname_group_header" id="user_nickname_group_header" value="${group.user_nickname_group_header}"
                           readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="group_person_count">참여인원</label>
                    <input class="form-control" name="group_person_count" id="group_person_count" value="${group.group_join_person_number}/${group.group_person_count}"
                           readonly="readonly">
                </div>


                <div class="form-group">
                    <label for="group_reg_date">그룹 생성일</label>

                    <input class="form-control" name="group_reg_date" id="group_reg_date"
                           value="<fmt:parseDate var="date1" value="${group.group_reg_date}" pattern="yyyy-MM-dd"/><fmt:formatDate value="${date1}" type="DATE" pattern="yyyy-MM-dd"/>"
                           readonly="readonly">

                </div>
                    <button data-oper='modify' class="btn btn-default"> 수정</button>
                    <button data-oper='list' class="btn btn-info">목록 </button>
                    <button data-oper='remove' class="btn btn-danger">그룹 파괴</button>
                    <button data-oper='remove' class="btn btn-block">탈퇴 하기</button>
                    <button data-oper='join' class="btn btn-warning">그룹 가입 </button>

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
                                <button class='remove_message btn btn-outline-danger btn-sm'>삭제</button>";
                                <form id = 'sendGroupMessage' action='/group/ajax/new' method='post'>";
                                    <div class = 'md-3'>";
                                        <label for = 'message-text' class='col-form-label'> 입력창 </label>";
                                        <textarea class='form-control' id='message-text'></textarea>";
                                    </div>
                                </form>";
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
            $(operForm).attr("action","/group/modify").attr("method","get").submit(); //수정으로 돌아기기
        })

        $(".btn-info").click(function (){
            $(operForm).find("#group_id").remove();
            $(operForm).attr("action", "/group/list").attr("method","get").submit(); //리스트로 돌아가기
        })

        if(${group.user_id_group_header eq user_id}) {
            console.log("아이디가 같나?")
            $('.btn-warning').attr('hidden', true)
            $('.btn-block').attr('hidden', true)
        }else{
            $('.btn-danger').attr('hidden', true)
            $('.btn-default').attr('hidden', true)
        }
        if(${join >=1}){
            $('.btn-warning').attr('hidden',true);
        }else{
            $('.btn-block').attr('hidden', true)
        }

        $('.btn-warning').click(function (){
            console.log('그룹가입 버튼이 눌리나')

            if(${group.group_join_person_number == group.group_person_count}){
                alert('인원수가 초과되었습니다.')
                return false;
            }
            if(${group.group_is_secret==1}){
                alert('비밀번호를 입력하세요')
                return false;
            }
            $(operForm).attr("action","/group/read").attr("method","post").submit();  //회원가입
        })
        $('.btn-danger').click(function (){
            $(operForm).attr("action","/group/groupRemove").attr("method","post").submit();  //그룹 파괴
        })
        $('.btn-block').click(function (){
            $(operForm).attr("action","/group/userRemove").attr("method","post").submit();  //탈퇴하기
        })

        var group_id = '${group.group_id}'

        $("#modalShowButton").click(function (){
            $('.modal').modal("show")
            messageService.getList(group_id,function(result){
                for(var i = 0; i<result.length; i++){
                    var text = "";
                    // text += "<button class='remove_message btn btn-outline-danger btn-sm'>삭제</button>";
                    // text += "<form id = 'sendGroupMessage' action='/group/ajax/new' method='post'>";
                    // text += "<div class = 'md-3'>";
                    // text += "<label for = 'message-text' class='col-form-label'> 입력창 </label>";
                    // text += "<textarea class='form-control' id='message-text'></textarea>";
                    // text += "</div></form>";
                    // $('.modal-body').html(text);
                }
            });
        })

        $(".remove_message").click(function (){
            console.log("삭제 버튼 클릭")
        })

        $("#modal_close").click(function (){
            $('.modal').modal("hide")
        })
    })
</script>

