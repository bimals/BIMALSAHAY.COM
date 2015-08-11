<div class="collapse navbar-collapse" ng-controller="HeaderController">
    <ul class="nav navbar-nav">
        <li ng-class="{ active: isActive('/')}"><a href="/web">Home</a></li>
        <li ng-class="{ active: isActive('/dogs')}"><a href="/blog">Blog</a></li>
        <li ng-class="{ active: isActive('/cats')}"><a href="/">Admin</a></li>
    </ul>
</div>
<div ng-view></div>