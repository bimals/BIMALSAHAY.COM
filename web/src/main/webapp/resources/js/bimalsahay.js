'use strict';

var app = angular.module("bs", ['angularFileUpload','ngRoute', 'infinite-scroll'])

app.controller("RegistrationController", [ '$scope', '$http',
		function($scope, $http) {

			$scope.registerUser = function() {

				var userObj = {
					userName : $scope.inputEmail,
					password : $scope.password,
					firstName : $scope.firstName,
					lastName : $scope.lastName
				};
				var res = $http.post('/web/account/user/create', userObj);
				res.success(function(data, status, headers, config) {
					$scope.message = data;
					$scope.inputEmail = '';
					$scope.password = '';
					$scope.firstName = '';
					$scope.lastName = '';
					window.location = '/web';
				});
				res.error(function(data, status, headers, config) {
					alert("failure message: " + JSON.stringify({
						data : data
					}));
				});
				// Making the fields empty
				//

			};
		} ]);

app.controller("HomeController", [ '$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	
		$scope.products = [];
		$scope.after;
		$scope.busy = true;

	   $scope.isLoggedIn = function() {

		      $http.get('/web/account/user/checklogin')
		        .success(function(data) {
		          console.log(data);
		          $rootScope.loggedIn = data;
		        })
		        .error(function(data) {
		          console.log('error: ' + data);
		        });
		    };
		   
		$scope.search = function(skip) {

			var searchObj = {
				searchText : $scope.searchText,
				skip : skip,
				
			};
			var res = $http.post('/web/product/search', searchObj);
			res.success(function(data, status, headers, config) {
				for (var int = 0; int < data.length; int++) {
					$scope.products.push(data[int]);
				}
				$scope.after = $scope.products[$scope.products.length - 1].id;
			    $scope.busy = false;
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
		};
		
		$scope.searchMore = function(skip) {
			if (this.busy)  {
				$scope.busy = true;
				return;
			}
				
			var searchObj = {
				searchText : $scope.searchText,
				skip : skip,
				after : $scope.after
			};
			var res = $http.post('/web/product/search', searchObj);
			res.success(function(data, status, headers, config) {
				for (var int = 0; int < data.length; int++) {
					$scope.products.push(data[int]);
				}
				
				if(data.length == 0) {
					$scope.busy = true;
				}
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
		};	
} ]);

app.directive("oneBlog", function() {
	return {
		restrict : 'E',
		templateUrl : '/web/account/user/login'
	}
});

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	// Home
	.when("/", {
		templateUrl : "/web/resources/partials/home.html",
		controller : "PageCtrl"
	})
	// Pages
	.when("/about", {
		templateUrl : "/web/resources/partials/about.html",
		controller : "PageCtrl"
	}).when("/faq", {
		templateUrl : "/web/resources/partials/faq.html",
		controller : "PageCtrl"
	}).when("/pricing", {
		templateUrl : "/web/resources/partials/pricing.html",
		controller : "PageCtrl"
	}).when("/services", {
		templateUrl : "/web/resources/partials/services.html",
		controller : "PageCtrl"
	}).when("/contact", {
		templateUrl : "/web/resources/partials/contact.html",
		controller : "PageCtrl"
	}).when("/registration", {
		templateUrl : "/web/resources/partials/registration.html",
		controller : "PageCtrl"
	}).when("/login", {
		templateUrl : "/web/resources/partials/login.html",
		controller : "PageCtrl"
	}).when("/customdesign", {
		templateUrl : "/web/resources/partials/customdesign.html",
		controller : "PageCtrl"
	}).when("/selljwel", {
		templateUrl : "/web/resources/partials/selljwel.html",
		controller : "PageCtrl"
	}).when("/newdesign", {
		templateUrl : "/web/resources/partials/addcustomdesign.html",
		controller : "PageCtrl"
	}).when("/home", {
		templateUrl : "/web/resources/partials/home.html",
		controller : "PageCtrl"
	})
	
	
	
	// Blog
	.when("/all", {
		templateUrl : "/web/resources/partials/blog-home-2.html",
		controller : "BlogCtrl"
	}).when("/web/post", {
		templateUrl : "/web/resources/partials/post.html",
		controller : "BlogCtrl"
	}).when("/new", {
		templateUrl : "/web/resources/partials/newblog.html",
		controller : "BlogCtrl"
	}).when("/read", {
		templateUrl : "/web/resources/partials/blog_item.html",
		controller : "BlogCtrl"
	}).when("/drafts", {
		templateUrl : "/web/resources/partials/drafts.html",
		controller : "BlogCtrl"
	})
	
	// else 404
	.otherwise("/404", {
		templateUrl : "/web/resources/partials/404.html",
		controller : "PageCtrl"
	});
} ]);

/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function(/* $scope, $location, $http */) {
	console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function(/* $scope, $location, $http */) {
	console.log("Page Controller reporting for duty.");

	// Activates the Carousel
	$('.carousel').carousel({
		interval : 5000
	});

	// Activates Tooltips for Social Links
	$('.tooltip-social').tooltip({
		selector : "a[data-toggle=tooltip]"
	})
});


app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);



app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, data){
        var fd = new FormData();
        fd.append('fileToUpload', file);
        fd.append('productId', data.id);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        	window.location = '#/';
        })
        .error(function(){
        });
    }
}]);

app.controller('myCtrl', ['$http','$scope', 'fileUpload', function($http, $scope, fileUpload){
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        var files = angular.element($('#js-upload-files')).files;
        console.log('file is ' );
        console.dir(file);
        console.log(files);
        var uploadUrl = "/web/user/addproductimage";
		var productObj = {
				productName : $scope.productName,
				productDescription : $scope.productDescription
			};
		var res = $http.post('/web/user/createproduct', productObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			$scope.productId = data._id;
			$scope.inputEmail = '';
			fileUpload.uploadFileToUrl(file, uploadUrl, data);
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});		
    };
    
}]);


$('.carousel').carousel({
    interval: 5000 //changes the speed
})

function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	}


app.controller('AppController', ['$http', '$scope', 'FileUploader', function($http, $scope, FileUploader) {
	
	$scope.checkForDraftBlogs = function() {
		var blogObj = {
				blogTitle : $scope.blogTitle,
				blogWebsite : $scope.blogWebsite,
				blogYouTube : $scope.blogYouTube,
				blogText : $scope.blogText,
				blogMoreText : $scope.blogMoreText
			};
			var res = $http.post('/web/blog', blogObj);
			res.success(function(data, status, headers, config) {
				$scope.drafts = data;
				$scope.inputEmail = '';
				$scope.password = '';
				$scope.firstName = '';
				$scope.lastName = '';
				window.location = '#/drafts';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	$scope.addDraftBlog = function() {
		var blogObj = {
				blogTitle : $scope.blogTitle,
				blogWebsite : $scope.blogWebsite,
				blogYouTube : $scope.blogYouTube,
				blogText : $scope.blogText,
				blogMoreText : $scope.blogMoreText
			};
			var res = $http.post('/web/user/draft', blogObj);
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				$scope.inputEmail = '';
				$scope.password = '';
				$scope.firstName = '';
				$scope.lastName = '';
				window.location = '#/new';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	
	$scope.publishBlog = function() {
		var blogObj = {
				blogTitle : $scope.blogTitle,
				blogWebsite : $scope.blogWebsite,
				blogYouTube : $scope.blogYouTube,
				blogText : $scope.blogText,
				blogMoreText : $scope.blogMoreText
			};
			var res = $http.post('/web/user/createblog', blogObj);
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				$scope.inputEmail = '';
				$scope.password = '';
				$scope.firstName = '';
				$scope.lastName = '';
				window.location = '#/web/post';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	
	
    var uploader = $scope.uploader = new FileUploader({
        url: '/web/user/addproductimage'
    });

    // FILTERS

    uploader.filters.push({
        name: 'imageFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    });

    // CALLBACKS

    uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        console.info('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function(fileItem) {
        console.info('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function(addedFileItems) {
        console.info('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function(item) {
        console.info('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function(fileItem, progress) {
        console.info('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function(progress) {
        console.info('onProgressAll', progress);
    };
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        console.info('onSuccessItem', fileItem, response, status, headers);
    };
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function(fileItem, response, status, headers) {
        console.info('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function(fileItem, response, status, headers) {
        console.info('onCompleteItem', fileItem, response, status, headers);
    };
    uploader.onCompleteAll = function() {
        console.info('onCompleteAll');
    };

    console.info('uploader', uploader);
}]);


app.directive('ngThumb', ['$window', function($window) {
        var helper = {
            support: !!($window.FileReader && $window.CanvasRenderingContext2D),
            isFile: function(item) {
                return angular.isObject(item) && item instanceof $window.File;
            },
            isImage: function(file) {
                var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        };

        return {
            restrict: 'A',
            template: '<canvas/>',
            link: function(scope, element, attributes) {
                if (!helper.support) return;

                var params = scope.$eval(attributes.ngThumb);

                if (!helper.isFile(params.file)) return;
                if (!helper.isImage(params.file)) return;

                var canvas = element.find('canvas');
                var reader = new FileReader();

                reader.onload = onLoadFile;
                reader.readAsDataURL(params.file);

                function onLoadFile(event) {
                    var img = new Image();
                    img.onload = onLoadImage;
                    img.src = event.target.result;
                }

                function onLoadImage() {
                    var width = params.width || this.width / this.height * params.height;
                    var height = params.height || this.height / this.width * params.width;
                    canvas.attr({ width: width, height: height });
                    canvas[0].getContext('2d').drawImage(this, 0, 0, width, height);
                }
            }
        };
    }]);
