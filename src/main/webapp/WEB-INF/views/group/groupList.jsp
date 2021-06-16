<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <title>그룹</title>
</head>
<body>
    <div class="row">
        <div class="col-sm-12">
            <h2>그룹메세지</h2>
            <c:forEach var="list" items="${list}">
                <div>
                    <row>
                            ${list.user_nickname}
                            ${list.group_message_reg_date}
                    </row>
<%-- 본인일경우       <row> ${list.group_message_reg_date}  ${list.user_nickname}</row>--%>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>




<%@ include file="../includes/footer.jsp" %>