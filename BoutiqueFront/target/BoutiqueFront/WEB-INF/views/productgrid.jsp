<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />
	<c:set var="s"
	value="${pageContext.request.contextPath}" />
	

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<c:forEach var="pl" items="${productlist}">

				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<h4 class="text-center">
							<span class="label label-info">${pl.productName}</span>
						</h4>
						<img src="${CR}/${pl.productId}.jpg" class="img-responsive">
						<div class="caption">
							<div class="row">
								<div class="price">
									<h3 class="text-center">&#8377 ${pl.price} /-
									</h3>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<a href='${s}/info/${pl.productName}' class="btn btn-primary btn-product"><span
										class="glyphicon glyphicon-info-sign"></span> Info</a>
								</div>
								<div class="col-md-6">
									<a href="#" class="btn btn-success btn-product"><span
										class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</a>
								</div>
							</div>

							<p></p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>

</div>