<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<i class="bi bi-lock-fill"></i>
<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>그룹</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-12">
            <h2>스터디 그룹</h2>
            <form id="operForm" action="/group/create" method="get">
            <button id="groupBtn" type="button" class="btn pull-right btn-warning">그룹 생성</button>
            </form>
            <table width="100%" class="table table-striped table-bordered table-hover"
                   id="dataTables-example">
                <div class="row">
                    <div class="col-lg-12">
                        <form id="searchForm" action="/board/list" method="get">
                            <select name="type">
                                <option value="" <c:out value="${pageMaker.cri.type ==null?'selected':''}"/>>---</option>
                                <option value="T"
                                        <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>그룹 이름</option>
                                <option value="C"
                                        <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>카테고리</option>
                                <option value="W"
                                        <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>그룹장</option>
                                <option value="TC"
                                        <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>그룹 이름 또는 카테고리 </option>
                                <option value="TW"
                                        <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>그룹 이름 또는 그룹장 </option>
                                <option value="TWC"
                                        <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>그룹 이름 또는 그룹장 또는 카테고리  </option>
                            </select>
                            <input type="text" name="keyword"/>
                            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" >
                            <input type="hidden" name="amount" value="${pageMaker.cri.amount}" >
                            <button class='btn btn-default'>검색</button>
                        </form>
                <thead>
                <tr>
                    <th>번호</th>
                    <th>그룹 이름</th>
                    <th>그룹장</th>
                    <th>카테고리</th>
                    <th>참여인원</th>
                    <th>생성일자</th>

                </tr>
                </thead>
                <c:forEach items="${group}" var="group" end="${group.size()}">
                    <tr>
                        <td>${group.group_id}</td>
                        <td><a class='move' href='read=${group.group_id}'>${group.group_name}
                        </a></td>
                        <td>${group.user_id_group_header}</td>
                        <td>${group.group_category}</td>
                        <td>3 /${group.group_person_count}
                            <c:if test="${group.group_is_secret==1}">
                                <i class="fa fa-lock"  aria-hidden="true"/>
                            </c:if>
                        </td>
                        <td><fmt:parseDate var="date1" value="${group.group_reg_date}" pattern="yyyy-MM-dd"/>
                            <fmt:formatDate value="${date1}" type="DATE" pattern="yyyy-MM-dd"/>
                                ${date2}
                        </td>
                    </tr>
                </c:forEach>
            </table>

<%--            <c:forEach var="group" items="${group}">--%>
<%--                <div>--%>
<%--                    <row>--%>
<%--                            ${list.user_nickname}--%>
<%--                            ${list.group_message_reg_date}--%>
<%--                    </row>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
        </div>
    </div>
</body>
</html>

<script>
    $(document).ready(function (){
        $("#groupBtn").click(function (){
            console.log("버튼 눌리나")
            $("#operForm").attr("action", "/group/create").submit();
        })
    })

</script>



<%@ include file="../includes/footer.jsp" %>