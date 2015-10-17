define(['directives', 'makeitsocial'], function (directives) {

    directives.directive("misbtn", ['$rootScope', function ($rootScope) {
        return {
            restrict: "A",
            link: function (scope, elem, attrs) {
                $rootScope.log("mks");
                MISbtn.url = 'https://popup-sandbox.herokuapp.com';
                MISbtn.register();
            }
        }
    }]);

});
