<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="includes/header.jsp" %>




<div class="row">
    <div class="col-sm-12">

        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" id="Alarm">
            Launch static backdrop modal
        </button>



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
                    <tbody>
                    <c:forEach items="${list}" var="list">
                    <tr>
                        <td style="font-size: 12px;"class="align-middle">
                        <fmt:formatDate pattern="yyyy/MM/dd" value="${list.alarm_message_mod_date}" /></td>
                        <td><div style="white-space: nowrap; overflow: hidden;text-overflow: ellipsis; width:450px;">${list.alarm_message_content} dmdmdkffmdksmdkfmskd</div></td>
                        <td>${list.alarm_message_is_new}</td>
                    </tr>
                    </c:forEach>
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

        $("#Alarm").click(function (){
            $('#staticBackdrop').modal("show");
        });

    });
</script>



<%@ include file="includes/footer.jsp" %>