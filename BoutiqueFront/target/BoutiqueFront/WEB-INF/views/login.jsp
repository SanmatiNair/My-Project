<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<div class= "container">
<div class="row col-md-6 col-md-offset-2 custyle">
<form class="form-horizontal" action='perform_login' method="POST">
    <div class="form-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="usename" name="usename" placeholder="" class="form-control input-xlarge">
       </div>
    </div>
 
    <div class="form-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="userpassword" name="userpassword" placeholder="" class="form-control input-xlarge">
       </div>
    </div>
    <div class="form-group">
      <label class="control-label"  for="submit"></label>
      <div class="controls">
        <input type="submit" id="submit" value="Sign In" name="submit" class="btn btn-success">
       </div>
    
          </div>
          <div class="form-group">
          <label class="control-label" >New User?</label>
          <div class="controls">
          <a href="${contextRoot}/registration" class="btn btn-primary">Sign Up</a>
          </div>
          </div>
</form>
</div>
</div>