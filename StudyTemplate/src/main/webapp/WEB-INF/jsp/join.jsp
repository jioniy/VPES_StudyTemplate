<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="title" content="<spring:message code='study.template.title' />" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<!-- 캐싱 방지  -->
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="pragma" content="no-store" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Expires" content="-1" />



<title><spring:message code='study.template.title' /></title>

<!-- JS -->
<script src="webjars/jquery/2.1.4/dist/jquery.min.js"></script>
<script src="webjars/vue/2.6.12/vue.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="shortcut icon" type="image/x-icon" href="image/favicon.ico" />
<link href="lib/css/join.css" rel="stylesheet" type="text/css">
<!-- STYLE -->
<style>
</style>

</head>

<body style= "background-color:#F0f3f7;">
	<div class="container" id ="joinVue">
		<div class="row" style="padding-top:6%;padding-bottom:6%;">
			<div style="text-align:center;"><img width="287px" height="80px" src="image/logo.png"></div><!-- 페이지 로고 -->
		</div>
		<div style="margin-bottom:10px;margin-left:25%; margin-right:25%;padding-left:1%;">
			<img src="image/user.png" style="display:inline-block; filter:brightness(0.5);">
			<div class="subtitle-area" style="display:inline-block;margin-top:1px;">
				기본 정보
			</div>
		</div>
		<div style="background-color:#FFFFFF;margin-left:25%; margin-right:25%;border-radius:5px;">
			<div class="card">
				<div class="grid grid-custom" style="padding:3%;">
					<div class="row top-value input-area">
						<div class="col-md-3 label-area">
							<span style="color:red; margin-left:-5px;">*</span>
							<spring:message code="study.template.page.join.join.username" />
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="text" v-model="name" ref="usernameRef" maxlength = "20" style="width:100%;"><br>
						</div>
					</div>
					<div class="row input-area">
						<div class="col-md-3 label-area">
							<span style="color:red; margin-left:-5px;">*</span>
							<spring:message code="study.template.page.join.join.id" />
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<div style="width:100%;">
								<input type="text" v-model="userId" ref="userIdRef" :class="[idWarning ? 'red-warning': 'black-standard']" maxlength="20" @change="checkIdCondition()"style="width:75%;">
								<button v-on:click="checkOverlappedId()" style="margin-left:1%; height:35px;width:22%;font-size:12px; color:#969696; border: solid 1px #969696;background-color:#ffffff;">중복확인</button><br>
							</div>							
							<div v-if="isUserIdKorean" class="alert-area" style="color:red;"><div class="info">i</div>영문자, 숫자만 입력하세요.</div>
							<div v-else-if="underUserIdLength" class="alert-area" style="color:red;"><div class="info">i</div>8자 이상 입력하세요.</div>
						</div>
					</div>
				</div>
				<div class="line"></div>
				<div class="grid grid-custom" style="padding:3%;">
					<div class="row container-value input-area">
						<div class="col-md-3 label-area">
							<span style="color:red; margin-left:-5px;">*</span>
							<spring:message code="study.template.page.join.join.passwd" />
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="password" v-model="pw" ref="pwRef" :class="[pwWarning ? 'red-warning': 'black-standard']" maxlength="20" @change="checkPwCondition()" style="width:100%;"><br>
							<div v-if="unavailablePw" class="alert-area" style="color:red;"><div class="info">i</div>한글을 제외하고 영문자, 숫자를 모두 포함해야합니다.</div>
							<div v-else-if="underPwLength" class="alert-area" style="color:red;"><div class="info">i</div>8자 이상 입력하세요.</div>
						</div>
					</div>
					<div class="row input-area">
						<div class="col-md-3 label-area">
							<span style="color:red; margin-left:-5px;">*</span>
							<spring:message code="study.template.page.join.join.passwdck" />
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="password" v-model="pw_ck" ref="pwCkRef" :class="[pwCkWarning ? 'red-warning': 'black-standard']"maxlength="20" @change="checkPwCondition()" style="width:100%;"><br>
							<div v-if="incorrectPw" class="alert-area" style="color:red;"><div class="info">i</div>비밀번호가 다릅니다.</div>
						</div>
					</div>
				</div>
				<div class="line"></div>
				<div class="grid grid-custom" style="padding:3%;">
					<div class="row container-value input-area">
						<div class="col-md-3 label-area">
							부서
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="text" v-model="department" maxlength="20" style="width:100%;"><br>
						</div>
					</div>
					<div class="row input-area">
						<div class="col-md-3 label-area">
							직급
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="text" v-model="rank"  maxlength="20" style="width:100%;"><br>
						</div>
					</div>
					<div class="row input-area">
						<div class="col-md-3 label-area">
							직책
						</div>
						<div class="col-md-8 col-xs-12" style="padding-right:0px;">
							<input type="text" v-model="position" maxlength="20" style="width:100%;"><br>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="margin-left:25%; margin-right:25%;border-radius:5px;">
			<div class="grid">
				<div class="row">
					<div class="col-md-12">
						<button v-on:click="joinClicked()" class="submit-btn">등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
<script src="lib/js/join.js"></script>

</html>