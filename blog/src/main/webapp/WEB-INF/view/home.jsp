<!DOCTYPE html>
<html ng-app="blogApp" style="padding-left: 350px; padding-right: 350px;">
<style>
element.style {
  padding-top: 20px;
  padding-left: 350px;
  border-right-width: 200px;
  padding-right: 350px;
}

body {
  display: block;
  margin: 8px;
}
</style>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js">
	
	</script>
	<script type="text/javascript">
		var blogApp = angular.module("blogApp", []).controller('blogController', ['$scope', function($scope) {
			$scope.name = "Bimal Sahay";	
			$scope.text = "A process has a self-contained execution environment. A process generally has a complete, private set of basic run-time resources; in particular, each process has its own memory space. Processes are often seen as synonymous with programs or applications. However, what the user sees as a single application may in fact be a set of cooperating processes. To facilitate communication between processes, most operating systems support Inter Process Communication (IPC) resources, such as pipes and sockets. IPC is used not just for communication between processes on the same system, but processes on different systems.Most implementations of the Java virtual machine run as a single process. A Java application can create additional processes using a ProcessBuilder object. Multiprocess applications are beyond the scope of this lesson."
			
				$scope.showAllert = function() {
				alert('Window');
			}
		
		}]);
		

		
		blogApp.directive("oneBlog", function(){
			return {
				restrict:'E',
				templateUrl:'one-blog'				
			}
		});
		
		function showAllert() {
			alert('Window');
		}
	</script>
	<body>
		<one-blog></one-blog>
		<div ng-controller="blogController as blog">
			<p>Name : <input type="text" ng-model="blog.name"></p>
			<p>Text : <textarea ng-model="blog.text"></textarea></p>
	  		<h1>Hello {{blog.name}}</h1>
	  		<p>{{$blog.text}}
	  		<button ng-click="showAllert()">Test</button>
	  	</div>
  		
	</body>
	
</html>
