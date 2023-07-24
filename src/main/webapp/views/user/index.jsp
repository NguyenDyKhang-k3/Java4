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
<title>Online Entertainment</title>
<%@ include file="/common/head.jsp"%>

</head>

<body>
	<main>
		<%@ include file="/common/header.jsp"%>
		<section class="hero-section light-background">
			<div class="section-overlay"></div>

			<div
				class="container d-flex justify-content-center align-items-center">
				<div class="row">
					<div class="col-12 mt-auto mb-5 text-center">
						<small>FESTIVAL ENTERTAINMENT</small>

						<h1 class="text-white mb-5">Latest Best Movies 2023</h1>

						<a class="btn custom-btn smooth-scroll" href="#section_3">Let's
							begin</a>
					</div>

					<div
						class="col-lg-12 col-12 mt-auto d-flex flex-column flex-lg-row text-center">
						<div class="date-wrap">
							<h5 class="text-white">
								<i class="custom-icon bi bi-calendar3 me-2"></i> Coming Soon
							</h5>
						</div>

						<div class="location-wrap mx-auto py-3 py-lg-0">
							<h5 class="text-white">
								<i class="custom-icon bi bi-geo-alt me-2"></i> Your City,
								Country
							</h5>
						</div>

						<div class="social-share">
							<ul
								class="social-icon d-flex align-items-center justify-content-center">
								<span class="text-white me-3">Share:</span>

								<li class="social-icon-item"><a href="#"
									class="social-icon-link"> <span class="bi bi-facebook"></span>
								</a></li>

								<li class="social-icon-item"><a href="#"
									class="social-icon-link"> <span class="bi bi-twitter"></span>
								</a></li>

								<li class="social-icon-item"><a href="#"
									class="social-icon-link"> <span class="bi bi-instagram"></span>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="video-wrap">
				<video autoplay="" loop="" muted="" class="custom-video" poster="">
					<source src="<c:url value='/templates/user/video/p.mp4'/>"
						type="video/mp4" />
					Your browser does not support the video tag.
				</video>
			</div>
		</section>

		<section class="artists-section section-padding light-background">
			<div class="container-fluid tm-mt-60">
				<div class="row mb-4">
					<h2 class="col-6 tm-text-primary">List Videos</h2>
					<div class="col-6 d-flex justify-content-end align-items-center">
						<form action="" class="tm-text-primary">
							Page <input type="text" value="1" size="1"
								class="tm-input-paging tm-text-primary"> of 200
						</form>
					</div>
				</div>
				<div class="row tm-mb-90 tm-gallery">
					<c:forEach items="${videos}" var="video">

						<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">

							<figure class="effect-ming tm-video-item mb-0">
								<img
									src="<c:url value='${video.poster}' />"
									class="img-fluid">
<%-- 									src="<c:url value='/templates/user/images/img/img-02.jpg' />"
									class="img-fluid"> --%>
								<figcaption
									class="d-flex align-items-center justify-content-center">
									<a href="<c:url value='/video?action=watch&id=${video.href}'/>"
										class="text-dark fs-5 mt-2"
										style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
										${video.title } </a>
								</figcaption>
							</figure>
							<div class="d-flex justify-content-between tm-text-gray">
								<span class="tm-text-gray-light">${video.shares } share</span> <span>${video.views }
									views</span>
							</div>
						</div>
					</c:forEach>

				</div>
				<!-- row -->
				<div class="row tm-mb-90">
					<div
						class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
						<c:if test="${currenPage == 1}">
						<a href="javascript:void(0);"
							class="btn btn-primary tm-btn-prev mb-2">Previous</a>
						</c:if>
						<c:if test="${currenPage > 1}">
						<a href="index?page=${currenPage - 1}"
							class="btn btn-primary tm-btn-prev mb-2">Previous</a>
						</c:if>
						
						<div class="tm-paging d-flex">
							<ul class="pagination">
								<c:forEach varStatus="i" begin="1" end="${maxPage}">
									<li class="page-item"><a
										class="page-link ${currenPage == i.index ? 'active' : '' }"
										href="index?page=${i.index}">${i.index}</a></li>
								</c:forEach>

							</ul>
						</div>
						<c:if test="${currenPage == maxPage}">
						<a href="javascript:void(3);" class="btn btn-primary tm-btn-next">Next
							Page</a>
						</c:if>
						<c:if test="${currenPage < maxPage}">
						<a href="index?page=${currenPage + 1}" class="btn btn-primary tm-btn-next">Next
							Page</a>
						</c:if>
						
					</div>
				</div>
			</div>
			<!-- container-fluid, tm-container-content -->
		</section>

	</main>

	<%@ include file="/common/footer.jsp"%>

</body>

</html>