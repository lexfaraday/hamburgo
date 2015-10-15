define(['controllers'], function (controllers) {
    controllers.controller('MainCtrl',
        ['Settings', '$rootScope', '$scope',
            function (settings, $rootScope, $scope) {

                var vm = this;

                $rootScope.useMockData = false; //if true, data is loaded from data/*.json files.
                $rootScope.waitMode = false; //if true, layer with load animation is displayed.
                $rootScope.error = {code: "", message: ""}; //if not blank, a layer with error content is displayed.
                $rootScope.selectedLanguage = "ENG";


                vm.showLogs = true;

                $rootScope.log = function (message) {
                    if (vm.showLogs) {
                        console.log(message);
                    }
                }

            }]
    );
});
