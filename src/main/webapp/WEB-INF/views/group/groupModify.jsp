<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"> 그룹 수정 페이지 </div> <!-- /.panel-heading -->
            <div class="panel-body">
                <form action="/group/modify" role="form" method="post">


                    <div class="form-group">
                        <label for="group_id">번호</label>
                        <input class="form-control" name="group_id" id="group_id" value="${group.group_id}" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label for="group_name">
                        <input class="form-control" name="group_name" id="group_name" placeholder="그룹이름" value="${group.group_name}"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="group_category">그룹 카테고리</label><br>
<%--                        <input class="form-control" name="group_category" id="group_category"  value="${group.group_category}"/>--%>
                        <select id="group_category">
                            <option value="취업">취업</option>
                            <option value="토익">토익</option>
                            <option value="이직">이직</option>
                            <option value="자격증">자격증</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="group_is_secret">비밀방
                            <input type="checkbox"  name="group_is_secret" id="group_is_secret"  value="${group.group_is_secret}"/>
                            </label>

                    </div>
                    <div class="form-group">
                        <label for="group_password"> 비밀번호
                        <input class="form-control" name="group_password" id="group_password" placeholder="비밀번호" value="${group.group_password}"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="group_person_count">그룹 인원</label>
                        <select id="group_person_count">
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
<%--                        <input class="form-control" name="group_person_count" id="group_person_count"  value="${group.group_person_count}"/>--%>
                    </div>
                    <div class="form-group">
                        <label for="group_content"/>
                        <textarea class="form-control" rows="3" name="group_content"
                                  id="group_content" placeholder="그룹 소개">${group.group_content}</textarea>
                    </div>


<%--                    <div class="form-group">--%>
<%--                        <label for="group_mod_date">수정일</label>--%>
<%--                        <input type="text" class="form-control" name="updateDate"--%>
<%--                               value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>'--%>
<%--                               readonly="readonly">--%>
<%--                    </div>--%>
                    <button data-oper='modify' class="btn btn-default" type="submit"> 수정하기 </button>
                    <button data-oper='list' class="btn btn-info" type="submit">취소</button>
                </form>
            </div> <!-- end panel-body -->
        </div> <!-- end panel -->
    </div> <!-- col-lg-12 -->
</div> <!-- row -->
<%--<script type="text/javascript">
    $(document).ready(function(){
        var formObj = $("form");
        $("button").click(function(e){
            e.preventDefault(); //이벤트 잠시 멈춤
            var operation = $(this).data("oper");//위의 data- 이후의 값 추출
            console.log(operation);
            if(operation==='remove'){
                $("form").attr("action" ,"/board/remove").submit();
            } else if(operation ==='list'){
                $("form").attr("action" ,"/board/list").attr("method", "get")
                var pageNumTag = $("input[name='pageNum']").clone(); //복제하여 저장함
                var amountTag = $("input[name='amount']").clone(); //복제하여 저장함
                var keywordTag = $("input[name='keyword']").clone();
                var typeTag = $("input[name='type']").clone();
                formObj.empty().append(pageNumTag).append(amountTag)
                    .append(keywordTag).append(typeTag).submit();
            }
            formObj.submit();
        });
    });
</script>--%>
<%@ include file="../includes/footer.jsp" %>