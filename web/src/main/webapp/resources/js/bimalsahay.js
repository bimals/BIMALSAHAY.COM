'use strict';

var app = angular.module("bs", ['angularFileUpload','ngRoute', 'infinite-scroll', 'facebook']).config(function(FacebookProvider) {
     FacebookProvider.init('481717542008286');
  })

app.controller('authenticationCtrl', function($scope, Facebook) {

	
  });

app.controller("RegistrationController", [ '$scope', '$http',
		function($scope, $http) {

			$scope.registerUser = function() {

				var userObj = {
					userName : $scope.inputEmail,
					password : $scope.password,
					firstName : $scope.firstName,
					lastName : $scope.lastName
				};
				var res = $http.post('/bimalsahay/account/user/create', userObj);
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

app.controller("HomeController", [ '$scope', '$http', '$rootScope', 'Facebook', function($scope, $http, $rootScope, Facebook) {
	
		$scope.products = [];
		$scope.after;
		$scope.busy = true;
		
		$scope.fbLogout = function() {
			Facebook.logout(function(response) {
				  // user is now logged out
				});
			Window.location = "/bimalsahay/j_spring_security_check";
		}

	    $scope.login = function() {
	        // From now on you can use the Facebook service just as Facebook api says
	        Facebook.login(function(response) {
	          // Do something with response.
	        });
	      };

	      $scope.checkLoginState = function() {
	        Facebook.getLoginStatus(function(response) {
	          if(response.status === 'connected') {
	            $scope.loggedIn = true;
	          } else {
	            $scope.loggedIn = false;
	          }
	        });
	      };

	      $scope.me = function() {
	        Facebook.api('/me', function(response) {
	          $scope.user = response;
	        });
	      };
	      
	   $scope.isLoggedIn = function() {

	        Facebook.getLoginStatus(function(response) {
		          if(response.status === 'connected') {
		        	  $rootScope.loggedIn = true;
		        	  Facebook.api('/me?fields=id,first_name,last_name,friends,email,public_key', function(response) {
		        		      console.log('Successful login for: ' + response);
		        		      
		        				var userObj = {
		        						userName : response.email,
		        						password : "",
		        						firstName : response.first_name,
		        						lastName : response.last_name,
		        						userType : "facebook",
		        						userTypeId : response.id
		        					};
		        		      
		        		      $.ajax({
		        		    	    type: 'POST',
		        		    	    url: '/bimalsahay/account/user/create',
		        		    	    data: JSON.stringify (userObj),
		        		    	    success: function(data) {
		        		    	    	alert('data: ' + data); 
		        		    	    },
		        		    	    contentType: "application/json",
		        		    	    dataType: 'json'
		        		    	});
		        		      
		        		    });
		          } else {
		        	  $rootScope.loggedIn = false;
				      $http.get('/bimalsahay/account/user/checklogin')
				        .success(function(data) {
				          console.log(data);
				          $rootScope.loggedIn = data;
				        })
				        .error(function(data) {
				          console.log('error: ' + data);
				        });		        	  
		          }
		        });
		    };
		   
		$scope.search = function(skip) {

			var searchObj = {
				searchText : $scope.searchText,
				skip : skip,
				
			};
			var res = $http.post('/bimalsahay/product/search', searchObj);
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
			var res = $http.post('/bimalsahay/product/search', searchObj);
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
		templateUrl : '/bimalsahay/account/user/login'
	}
});

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	// Home
	.when("/", {
		templateUrl : "/bimalsahay/resources/partials/home.html",
		controller : "PageCtrl"
	})
	// Pages
	.when("/about", {
		templateUrl : "/bimalsahay/resources/partials/about.html",
		controller : "PageCtrl"
	}).when("/faq", {
		templateUrl : "/bimalsahay/resources/partials/faq.html",
		controller : "PageCtrl"
	}).when("/pricing", {
		templateUrl : "/bimalsahay/resources/partials/pricing.html",
		controller : "PageCtrl"
	}).when("/services", {
		templateUrl : "/bimalsahay/resources/partials/services.html",
		controller : "PageCtrl"
	}).when("/contact", {
		templateUrl : "/bimalsahay/resources/partials/contact.html",
		controller : "PageCtrl"
	}).when("/register", {
		templateUrl : "/bimalsahay/resources/partials/registration.html",
		controller : "PageCtrl"
	}).when("/login", {
		templateUrl : "/bimalsahay/resources/partials/login.html",
		controller : "PageCtrl"
	}).when("/customdesign", {
		templateUrl : "/bimalsahay/resources/partials/customdesign.html",
		controller : "PageCtrl"
	}).when("/selljwel", {
		templateUrl : "/bimalsahay/resources/partials/selljwel.html",
		controller : "PageCtrl"
	}).when("/newdesign", {
		templateUrl : "/bimalsahay/resources/partials/addcustomdesign.html",
		controller : "PageCtrl"
	}).when("/home", {
		templateUrl : "/bimalsahay/resources/partials/home.html",
		controller : "PageCtrl"
	})
	
	
	
	// Blog
	.when("/all", {
		templateUrl : "/bimalsahay/resources/partials/blog-home-2.html",
		controller : "BlogCtrl"
	}).when("/bimalsahay/post", {
		templateUrl : "/bimalsahay/resources/partials/post.html",
		controller : "BlogCtrl"
	}).when("/new", {
		templateUrl : "/bimalsahay/resources/partials/newblog.html",
		controller : "BlogCtrl"
	}).when("/read", {
		templateUrl : "/bimalsahay/resources/partials/blog_item.html",
		controller : "BlogCtrl"
	}).when("/drafts", {
		templateUrl : "/bimalsahay/resources/partials/drafts.html",
		controller : "BlogCtrl"
	}).when("/edit", {
		templateUrl : "/bimalsahay/resources/partials/editdraft.html",
		controller : "BlogCtrl"
	}).when("/myCarousel", {
		templateUrl : "/bimalsahay/resources/partials/editdraft.html",
		controller : "BlogCtrl"
	})
	
	
	// else 404
	.otherwise("/404", {
		templateUrl : "/bimalsahay/resources/partials/404.html",
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
        var uploadUrl = "/bimalsahay/user/addproductimage";
		var productObj = {
				productName : $scope.productName,
				productDescription : $scope.productDescription
			};
		var res = $http.post('/bimalsahay/user/createproduct', productObj);
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

app.service('blogService', ['$http', function($http){
	this.getAllPublishedBlogs = function() {
		var res = $http.post('/bimalsahay/blog');
		res.success(function(data, status, headers, config) {
				
		});
		res.error(function(data, status, headers, config) {
			window.location = '/bimalsahay/login/#/login';
		});
		
		return res;
	};
	
	this.getFullBlog = function(id) {
		var res = $http.get('/bimalsahay/blog/' + id);
		
		res.success(function(data, status, headers, config) {
			
		});
		res.error(function(data, status, headers, config) {
			window.location = '/bimalsahay/login/#/login';
		});
		
		
		return res;
	};
}]);

app.controller('AppController', ['$http', '$scope', 'FileUploader', '$rootScope', '$routeParams', 'blogService', function($http, $scope, FileUploader, $rootScope, $routeParams, blogService) {
	
	$scope.homePageBlogs = function() {
		blogService.getAllPublishedBlogs().then(function(response) {
			$scope.blogs = response.data;
		});
		window.location = '#/all';
	};
	
	$scope.readBlog = function(data) {
		window.location = '#/read';
		$rootScope.id = data.id;
		
	};
	
	$scope.fullBlog = function() {
		blogService.getFullBlog($rootScope.id).then(function(response) {
			$scope.blog = response.data;

		});
	};
	
	$scope.addComment = function(id) {
		var comment = $scope.comment;
		var res = $http.post('/bimalsahay/blog/' + id + "/comment", comment);
		res.success(function(data, status, headers, config) {
			$scope.blog.comments = data.comment;
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	}
 	
	$scope.editDraft = function() {
		var res = $http.get('/bimalsahay/blog/' + $rootScope.currentDraft);
		res.success(function(data, status, headers, config) {
			$scope.id = data.id;
			$scope.blogTitle = data.blogTitle;
			$scope.blogWebsite = data.blogWebsite;
			$scope.blogYouTube = data.blogYouTube;
			$scope.blogText = data.blogText;
			$scope.blogMoreText = data.blogMoreText;
		});
		res.error(function(data, status, headers, config) {
			alert("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};
		
	$scope.useThisTemplate = function(draft) {
		blogService.getFullBlog(draft.id).then(function(response) {
			$scope.id = response.data.id;
			$scope.blogTitle = response.data.blogTitle;
			$scope.blogWebsite = response.data.blogWebsite;
			$scope.blogYouTube = response.data.blogYouTube;
			$scope.blogText = response.data.blogText;
			$scope.blogMoreText = response.data.blogMoreText;
			$scope.creationTimeStamp = response.data.creationTimeStamp;
			$scope.userFullName = response.data.userFullName;
		});
		
		window.location = "#/edit";
	};
	
	$scope.addNew = function(draft) {
		$rootScope.useNew = true;
		window.location = "#/new";
	};
	
	$scope.checkForDraftBlogs = function() {
		if(!$rootScope.useNew) {
			var res = $http.post('/bimalsahay/blog/draft', blogObj);
			res.success(function(data, status, headers, config) {
				if(data.length != 0) {
					$scope.drafts = data;
					window.location = '#/drafts';
				}
				else {
					window.location = '#/new';
				}
			});
			res.error(function(data, status, headers, config) {
				window.location = '/bimalsahay/login/#/login';
			});
		} else {
			
			var blogObj = {
					blogTitle : $scope.blogTitle,
					blogWebsite : $scope.blogWebsite,
					blogYouTube : $scope.blogYouTube,
					blogText : $scope.blogText,
					blogMoreText : $scope.blogMoreText
				};
			
			var res = $http.post('/bimalsahay/user/draft', blogObj);
			res.success(function(data, status, headers, config) {
				$scope.id = data.id;
				$scope.blogTitle = data.blogTitle;
				$scope.blogWebsite = data.blogWebsite;
				$scope.blogYouTube = data.blogYouTube;
				$scope.blogText = data.blogText;
				$scope.blogMoreText = data.blogMoreText;
				window.location = '#/drafts';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
		}
	};
	
	$scope.addDraftBlog = function() {
		var blogObj = {
				id : $scope.id,
				blogTitle : $scope.blogTitle,
				blogWebsite : $scope.blogWebsite,
				blogYouTube : $scope.blogYouTube,
				blogText : $scope.blogText,
				blogMoreText : $scope.blogMoreText
			};
			var res = $http.post('/bimalsahay/user/draft', blogObj);
			res.success(function(data, status, headers, config) {
				$scope.id = data.id;
				$scope.blogTitle = data.blogTitle;
				$scope.blogWebsite = data.blogWebsite;
				$scope.blogYouTube = data.blogYouTube;
				$scope.blogText = data.blogText;
				$scope.blogMoreText = data.blogMoreText;
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
				id : $scope.id,
				blogTitle : $scope.blogTitle,
				blogWebsite : $scope.blogWebsite,
				blogYouTube : $scope.blogYouTube,
				blogText : $scope.blogText,
				blogMoreText : $scope.blogMoreText
			};
			var res = $http.post('/bimalsahay/user/createblog', blogObj);
			res.success(function(data, status, headers, config) {
				$rootScope.useNew = false;
				$rootScope.currentDraft = null;
				window.location = '#/bimalsahay/post';
			});
			res.error(function(data, status, headers, config) {
				alert("failure message: " + JSON.stringify({
					data : data
				}));
			});
	};
	
	
	
    var uploader = $scope.uploader = new FileUploader({
        url: '/bimalsahay/user/'+$rootScope.currentDraft+'/image'
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


function checkLoginState() {
	window.location = "/bimalsahay";
}
