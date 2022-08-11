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

<!-- STYLE -->
<style>

</style>
</head>

<body>
	<div class="container" style="width: 1024px; height: 768px; max-width: none !important; min-height: 45%; max-height: 45%;">
		<div class="col-xs-12" style="position: relative; margin-left: 8%;">
			<div class="row">
				<div style="display: none; width: 20%;"></div>
				<div style="width: 50%; display: inline-block;">
					<div class="row">
						<img width="100%" src="image/QUALITYSCROLL_VPES.png">
					</div>
				</div>
				<div style="padding-top: 3%; position: absolute; bottom: 0; right: 0; padding-bottom: 16px; width: 50%;">
					<div class="col-xs-9">
						<div class="row">
							<form name="mform" method="post" action="login">
								<legend>
									<spring:message code="study.template.page.login.login" />
								</legend>
								<div class="col-xs-12">
									<div class="row">
										<c:if test="${param.error ne null}">
											<div style="color: red">
												<spring:message code="study.template.page.login.login.InvalidCredentials" />
											</div>
										</c:if>
									</div>
								</div>
								<div class="col-xs-6">
									<div class="row" style="padding-left: 1px; padding-right: 1px;">
										<div class="input-group input-group-default" style="padding-bottom: 4px; border-radius: 0px !important;">
											<span class="input-group-addon" id="inputGroup-sizing-default" style="padding: 0px; border-radius: 0px !important;"><img src="image/memberId_black.png" style="width: 28px;"></span>
											<input type="text" id="username" name="username" class="form-control" placeholder="<spring:message code='study.template.page.login.login.id' />" aria-describedby="inputGroup-sizing-default" style="color: #fa6607; font-size: 15px; border-radius: 0px !important;">
										</div>
										<div class="input-group input-group-default" style="padding-bottom: 4px; border-radius: 0px !important;">
											<span class="input-group-addon" id="inputGroup-sizing-defaults" style="padding: 0px; border-radius: 0px !important;"><img src="image/memberPw_black.png" style="width: 28px;"></span>
											<input type="password" id="pwd" name="password" class="form-control" placeholder="<spring:message code='study.template.page.login.login.passwd' />" aria-describedby="inputGroup-sizing-defaults" style="color: #fa6607; font-size: 15px; border-radius: 0px !important;">
										</div>
									</div>
								</div>
								<div class="col-xs-3" style="height: 100%; min-height: 100%;">
									<div class="row" style="padding-left: 1px; padding-right: 1px;">
										<button class="btn btn-default btn-lg" type="submit" style="height: 72px; width: 100%; background-color: #fa6607; color: #fff; font-size: 15px; font-weight: 600; border-radius: 0px !important;">
											<spring:message code="study.template.page.login.login" />
										</button>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
						</div>
						<a href="${pageContext.request.contextPath}/join">회원가입</a>
						<a href="${pageContext.request.contextPath}/join">비밀번호 찾기</a>
					</div>					
				</div>
			</div>
		</div>
	</div>
	<div>
		<div class="loginFooter" style="text-align: right; padding-right: 4%;">
			<a href="http://www.suresofttech.com"><img src="image/logoFooter.png" alt="suresoft"></a>
			<p style="font-size: 1px;">
				Copyright ⓒ <a href="http://www.suresofttech.com">COPYRIGHT Suresoft Technologies Inc.</a>, ALL RIGHTS RESERVED.
			</p>						
		</div>
	</div>
</body>
</html>