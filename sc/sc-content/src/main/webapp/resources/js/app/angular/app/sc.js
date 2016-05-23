var sc = angular.module("StudyComp", ['ngRoute',
		'ngMessages', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngStorage',
		'toastr' ]);

sc.animation('.animate-sctn', function() {
	return {
		enter : function(element, done) {
			element.css('display', 'none');
			element.fadeIn(500, done);
			return function() {
				element.stop();
			};
		},
		leave : function(element, done) {
			element.fadeOut(0, done);
			return function() {
				element.stop();
			};
		}
	};
});

sc.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('HttpServiceInterceptor');
} ]);

sc.config([ '$routeProvider', routeConfig ]);

function routeConfig($routeProvider) {
	$routeProvider.when('/portal/home', {
		templateUrl : 'templates/app/home/home.tpl.html'
	}).when('/portal/login', {
		templateUrl : 'templates/app/auth/login.tpl.html'
	}).when('/portal/signup', {
		templateUrl : 'templates/app/auth/register.tpl.html'
	}).when('/portal/forgotpassword', {
		templateUrl : 'templates/app/auth/forgot-password.tpl.html'
	}).otherwise({
		templateUrl : 'templates/app/home/home.tpl.html',
	});

	//
};

var httpCallArray = [];

function pop(item) {
	var index = -1;
	for (var i = 0; i < httpCallArray.length; i++) {
		if (item == httpCallArray[i]) {
			index = i;
		}
	}
	removeFromArray(httpCallArray, index, 1);
}

function push(item) {
	httpCallArray.push(item);
}

sc.factory('HttpServiceInterceptor', [
		'$rootScope',
		'$log',
		'$q',
		'$localStorage',
		function(rootScope, $log, $q, localStorage) {
			function isServiceCall(config) {
				if (config.url.indexOf('sc-service/rest/') != -1
						&& config.url.indexOf('mask=false') == -1) {
					return true;
				} else {
					return false;
				}
			}

			function isHTMLCall(config) {
				if (config.url.indexOf('.html') != -1) {
					return true;
				} else {
					return false;
				}
			}

			function show() {
				if (!$('#loading').is(':visible')) {
					$('#loading').show();
				} else {
					if (httpCallArray.length > 0) {
						$('#loading').show();
					}
				}
			}

			function hide() {
				if (httpCallArray.length == 0) {
					setTimeout(function() {
						$('#loading').hide();
					}, 10);
				} else {
					// $log.warn('Call in Progress');
				}
			}
			var serviceInterceptor = {
				request : function(config) {
					return config;
				},
				response : function(response) {
					return response;
				},
				responseError : function(response) {
					return $q.reject(response);
				}
			};
			return serviceInterceptor;
		} ]);
