<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header class="site-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-12 d-flex flex-wrap">
				<p class="d-flex me-4 mb-0">
					<i class="bi bi-person custom-icon me-2"></i> <strong
						class="text-dark">Welcome to the great movie website 2023</strong>
				</p>
			</div>
		</div>
	</div>
</header>

<nav class="navbar navbar-expand-lg">
	<div class="container">
		<a class="navbar-brand" href="index">Online Entertainment</a><a
			href="index" class="btn custom-btn d-lg-none ms-auto me-4">Buy
			Ticket</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav align-items-lg-center ms-auto me-lg-5">
				<li class="nav-item"><a class="nav-link click-scroll"
					href="index">Home</a></li>
				<li class="nav-item"><a class="nav-link click-scroll"
					href="favorites">My Favorites</a></li>
				<li class="nav-item"><a class="nav-link click-scroll"
					href="history">History</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="MyAccount"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<c:choose>
							<c:when test="${empty sessionScope.currentUser}">
               					 My Account
            				</c:when>
            				<c:when test="${not empty sessionScope.currentUser}">
               					 Xin chào ! khang
            				</c:when>
							<c:otherwise>
                				Xin chào ! <c:out value="${sessionScope.currentUser.username}" />
							</c:otherwise>
						</c:choose>
				</a>
					<ul class="dropdown-menu dropdown-menu-dark disabled"
						aria-labelledby="MyAccount">
						<c:choose>
							<c:when test="${empty sessionScope.currentUser}">
								<li><a class="dropdown-item" href="login">Login</a></li>
								<li><a class="dropdown-item" href="forgot-pass">Forgot
										Password</a></li>
								<li><a class="dropdown-item" href="register">Register</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="dropdown-item" href="logout">Logoff</a></li>
								<li><a class="dropdown-item" href="change-pass">Change
										Password</a></li>
								<li><a class="dropdown-item" href="#">Edit Profile</a></li>
							</c:otherwise>
						</c:choose>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>