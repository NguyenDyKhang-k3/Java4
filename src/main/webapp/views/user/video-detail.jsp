<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>${video.title}</title>
<%@ include file="/common/head.jsp"%>

</head>

<style>
#tm-video-container {
	max-height: 400px;
	overflow: hidden;
	background-color: #333;
	margin-bottom: 90px;
	position: relative;
}

#tm-video {
	display: block;
	width: 100%;
	height: auto;
}

#tm-video-control-button {
	position: absolute;
	bottom: 20px;
	right: 20px;
	z-index: 1000;
	color: #e1e1e1;
}

.tm-video-details {
	height: 100%;
	padding: 40px;
}

.tm-video-item {
	position: relative;
	overflow: hidden;
	text-align: center;
	cursor: pointer;
}

.tm-video-item img {
	position: relative;
	display: block;
	min-height: 100%;
	max-width: 100%;
	opacity: 0.8;
}

.tm-video-item figcaption {
	padding: 2em;
	color: #fff;
	text-transform: uppercase;
	font-size: 1.25em;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
}

.tm-video-item figcaption::before, .tm-video-item figcaption::after {
	pointer-events: none;
}

.tm-video-item figcaption, .tm-video-item figcaption>a {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.tm-video-item figcaption>a {
	z-index: 1000;
	text-indent: 200%;
	white-space: nowrap;
	font-size: 0;
	opacity: 0;
}

.tm-video-item h2 {
	word-spacing: -0.15em;
	font-weight: 300;
}

.tm-video-item h2, .tm-video-item p {
	margin: 0;
}

.tm-video-item p {
	letter-spacing: 1px;
	font-size: 68.5%;
}

.tm-gallery div.d-block {
	animation: show .5s ease;
}

@
keyframes show { 0% {
	opacity: 0;
	transform: scale(0.9);
}
100
%
{
opacity
:
1;
transform
:
scale
(
 
1
);
}
}
</style>

<body>
	<main>

		<header class="site-header">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 col-12 d-flex flex-wrap">
						<p class="d-flex me-4 mb-0">
							<i class="bi bi-person custom-icon me-2"></i> <strong
								class="text-dark">Welcome to the great movie website
								2023</strong>
						</p>
					</div>
				</div>
			</div>
		</header>

		<nav class="navbar navbar-expand-lg" style="background-color: black;">
			<div class="container">
				<a class="navbar-brand" href="index">Online Entertainment</a> <a
					href="ticket.html" class="btn custom-btn d-lg-none ms-auto me-4">Buy
					Ticket</a>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
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
									<c:otherwise>
                				Xin chào ! <c:out
											value="${sessionScope.currentUser.username}" />
									</c:otherwise>
								</c:choose>
						</a>
							<ul class="dropdown-menu dropdown-menu-dark disabled"
								aria-labelledby="MyAccount">
								<c:choose>
									<c:when test="${empty sessionScope.currentUser}">
										<li><a class="dropdown-item" href="login">Login</a></li>
										<li><a class="dropdown-item" href="#">Forgot Password</a></li>
										<li><a class="dropdown-item" href="register">Register</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="dropdown-item" href="logout">Logoff</a></li>
										<li><a class="dropdown-item" href="#">Change Password</a></li>
										<li><a class="dropdown-item" href="#">Edit Profile</a></li>
									</c:otherwise>
								</c:choose>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
		<section class="artists-section section-padding" id="section_2"
			style="height: 800px;">
			<div class="container-fluid tm-container-content tm-mt-60">
				<div class="row mb-3">
					<div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
						<h5 class="text-dark fs-5 mt-2">${video.title}</h2>
					</div>
				</div>
				<div class="row tm-mb-90">
					<div class="col-xl-8 col-lg-7 col-md-6 col-sm-12 rounded-3">
						<iframe id="tm-video" style="height: 556px;"
							src="https://www.youtube.com/embed/${video.href}" allowfullscreen></iframe>
					</div>
					<div class="col-xl-4 col-lg-5 col-md-6 col-sm-12 rounded-3"
						style="min-height: 500px !important; background: #f4f4f4;">
						<div class="tm-bg-gray tm-video-details p-4">
							<c:if test="${not empty sessionScope.currentUser}">
								<div class="text-center mb-4">
									<button id=likeOrUnlikeBtn class="btn btn-primary btn-lg px-5"
										style="min-width: 200px;">
										<c:choose>
											<c:when test="${flagLikeButton == true}">
											Unlike
										</c:when>
											<c:otherwise>
											Like
										</c:otherwise>
										</c:choose>
									</button>
								</div>
								<div class="text-center mb-4">
									<a href="#" class="btn btn-primary btn-lg px-5"
										style="min-width: 200px;">Share</a>
								</div>
							</c:if>
							<div class="mb-4">
								<h3 class="tm-text-gray-dark mb-3">Description</h3>
								<p class="text-dark">${video.description}</p>
							</div>
						</div>
					</div>

				</div>
			</div>
			<input id="videoIdHdn" type="hidden" value="${video.href}">
			<!-- container-fluid, tm-container-content -->
		</section>
	</main>

	<%@ include file="/common/footer.jsp"%>
	<script>
		$('#likeOrUnlikeBtn').click(function() {
			var videoId = $('#videoIdHdn').val();
			$.ajax({
				url : 'video?action=like&id=' + videoId
			}).then(function(data) {
				var text = $('#likeOrUnlikeBtn').text();
				if (text.indexOf('Like') != -1) {
					$('#likeOrUnlikeBtn').text('Unlike');
				} else {
					$('#likeOrUnlikeBtn').text('Like');
				}
			}).fail(function() {
				alert('Oops!!! Please try again');
			})
		});
	</script>
</body>

</html>