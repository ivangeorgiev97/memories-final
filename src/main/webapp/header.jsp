<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Memories</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle nav">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<!-- 	      <li class="nav-item active"> -->
				<li class="nav-item"><a class="nav-link" href="/memories.jsp">Спомени</a></li>
				<!-- <span class="sr-only">(current)</span></a> -->
				<li class="nav-item"><a class="nav-link" href="/categories.jsp">Категории</a></li>
				<li class="nav-item"><a class="nav-link" href="/users.jsp">Потребители</a>
				</li>
			</ul>
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name == null}">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/index.html">Вход</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="/registration.html">Регистрация</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
						<li class="nav-item"><btn id="logoutBtn" class="btn btn-link">Изход</btn></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
</header>
