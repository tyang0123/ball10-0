<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../includes/header.jsp" %>




<div class="container">
    <div class="col-sm-12">
        <h1>LOGIN</h1>

        <form method="post" action="/user/login">
            <div class="mb-3">
                <label for="user-id" class="form-label">ID</label>
                <input type="text" class="form-control" id="user-id" name="user_id">
                <div id="id-help" class="form-text" style="display: none;">영문자+숫자, 40글자</div>
            </div>
            <div class="mb-3">
                <label for="user-password" class="form-label">Password</label>
                <input type="password" class="form-control" id="user-password" name="user_password">
            </div>
            <input type="submit" class="btn btn-primary" style="margin-top: 1rem;" value="로그인">
        </form>
    </div>
</div>



<script>
    $(document).ready(function(){

        $("form div input").on('input', function(e){
            var regex = new RegExp("^[A-Za-z0-9]+$"); // alphabet and number
            if(!regex.test(e.target.value)){
                console.log("tick");
                var str = e.target.value;
                e.target.value= str.substring(0, str.length-1);
            }
        })

    })//end document.ready
</script>





<%@ include file="../includes/footer.jsp" %>