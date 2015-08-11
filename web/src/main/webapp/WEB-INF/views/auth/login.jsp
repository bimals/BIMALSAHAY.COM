	<div class="row-fluid" ng-app="loginApp" role="main">
	    <form class="form-horizontal" ng-controller="loginController" ng-submit="login()">
	        <div class="form-group">
	            <label for="inputEmail" class="control-label col-xs-2">Email</label>
	            <div class="col-xs-10">
	                <input type="email" class="form-control" id="inputEmail" placeholder="Email" ng-model="inputEmail">
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="inputPassword" class="control-label col-xs-2">Password</label>
	            <div class="col-xs-10">
	                <input type="password" class="form-control" id="inputPassword" placeholder="Password" ng-model="password">
	            </div>
	        </div>	          
	        <div class="form-group">
	            <div class="col-xs-offset-2 col-xs-10">
	                <div class="checkbox">
	                    <label><input type="checkbox"> Remember Me</label>
	                </div>
	            </div>
	        </div>
	        <div class="form-group">
	            <div class="col-xs-offset-2 col-xs-10">
	                <button type="submit" class="btn btn-primary">Login</button>
	            </div>
	        </div>
	    </form>
	</div>