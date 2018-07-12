<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/pimages" />

<div class="container">
	

	<div class="row">
		<div class="col-sm-5 ">

  <div> <img src="${CR}/${product.productId}.jpg" width="300px" height="300px"></div>
</div>
		<div class="col-sm-7">
<div class="card-body p-5">
	<h3 class="title mb-3">${product.productName}</h3>

<p class="price-detail-wrap"> 
	<span class="price h3 text-warning"> 
		<span class="currency">&#8377</span><span class="num">${product.price}</span>
	</span> 
</p> <!-- price-detail-wrap .// -->
<dl class="item-property">
  <dt>Description</dt>
  <dd><p>${product.productDescription}  </p></dd>
</dl>
 <!-- item-property-hor .// -->
<dl class="param param-feature">
  <dt>Color</dt>
  <dd>Black and white</dd>
</dl>  <!-- item-property-hor .// -->
<dl class="param param-feature">
  <dt>Delivery</dt>
  <dd>Free Shipping And COD For Coimbatore, Chennai AND Bangalore 
</dd>
</dl>  <!-- item-property-hor .// -->

<hr>
	<a href="#" class="btn btn-success btn-product"><span
				class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</a>
</div> <!-- card-body.// -->
		</div> <!-- col.// -->
	</div> <!-- row.// -->


</div>
