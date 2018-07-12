<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images"/>
<div class="container-fluid">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
                <li data-target="#myCarousel" data-slide-to="3"></li>
                <li data-target="#myCarousel" data-slide-to="4"></li>
                <li data-target="#myCarousel" data-slide-to="5"></li>
                <li data-target="#myCarousel" data-slide-to="6"></li>
                <li data-target="#myCarousel" data-slide-to="7"></li>
                <li data-target="#myCarousel" data-slide-to="8"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img class="first-slide home-image" src="${images}/suja111.jpg" alt="first slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>WELCOME TO THE Blush Boutique</h1>
                            <p >Order Now For Your Amazing New Arrivals</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="second-slide home-image" src="${images}/dress33.jpg" alt="second slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Boutique Store</h1>
                            <p>Range your Products</p>
                            <p>Make Up To Your Style!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="third-slide home-image" src="${images}/11.jpg" alt="third slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Boutique Store</h1>
                            <p>online shopping can make your life more easier</p>
                            <p>Transform It In Your Way!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="fourth-slide home-image" src="${images}/pic11.jpg" alt="fourth slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Dashing collections</h1>
                            <p>Variations of colours</p>
                            <p>Feel The Hotness In You!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="fifth-slide home-image" src="${images}/dress44.jpg" alt="fifth slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Stunning Way Of Look</h1>
                            <p>Be Better In Choosing </p>
                            <p>Curve Your Style!!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="sixth-slide home-image" src="${images}/22.jpg" alt="sixth slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>The Blushing Women </h1>
                            <p>For The Products Up to To The Mark</p>
                            <p> Style Is Found In You !!</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img class="seventh-slide home-image" src="${images}/66.jpg" alt="seventh slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Hot Deals </h1>
                            <p>Hurry Up</p>
                            <p>Standard!!</p>
                        </div>
                    </div>
                </div>
                
                <div class="item">
                    <img class="eight-slide home-image" src="${images}/33.jpg" alt="eight slide" height="100%" width="100%">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Come Again </h1>
                            <p>You are Valuable</p>
                            <p>Create Your Moment With Us!!</p>
                        </div>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" ></span>
                <span class="sr-only">Next</span>
            </a>
        </div><!-- /.carousel -->

</div>