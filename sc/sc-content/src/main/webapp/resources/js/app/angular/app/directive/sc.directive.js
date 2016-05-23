sc.directive('scCreative', [ '$rootScope', function(rootScope) {
	return {
		link : function(scope, elm, attr, ngModel) {
			$("h1").fitText(1.2, {
				minFontSize : '35px',
				maxFontSize : '65px'
			});

			$('#mainNav').affix({
				offset : {
					top : 100
				}
			})
			//new WOW().init();
		}
	};
} ]);