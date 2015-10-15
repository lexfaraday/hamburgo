define(['directives', 'wow'], function (directives) {

    directives.directive("wowInit", ['$rootScope', function ($rootScope) {
        return {
            restrict: "E",
            link: function (scope, elem, attrs) {
                $rootScope.log("WOW");
                new WOW().init();
            }
        }
    }]);

});
