define(['controllers', 'moment'], function (controllers, moment) {
    controllers.controller('SearcherCtrl',
        ['$rootScope', '$scope', '$state', 'HotelApiSrv', 'HotelContentApiSrv', 'MockDataSrv', 'Settings', '$timeout',
            function SearcherCtrl($rootScope, $scope, $state, HotelApiSrv, HotelContentApiSrv, MockDataSrv, Settings, $timeout) {

                var vm = this;
                vm.userLocation =  {
                    lat: 0,
                    lon: 0
                };
                vm.showDetails = false;
                vm.goTo = function (state) {
                    $rootScope.waitMode = true;

                    $rootScope.log($rootScope.waitMode);

                    $timeout(function () {
                        vm.wait(state);
                    }, 1000);

                };

                vm.wait = function (state) {

                    $timeout(function () {
                        $rootScope.log('Hide stage');

                        $('.event').toggleClass("animated flipOutX", function () {
                            $(this).remove();
                        });

                        $timeout(function () {
                            $rootScope.log('Redirect to next stage');
                            $rootScope.waitMode = false;
                            $state.go(state);
                        }, 1000);
                    }, 2000);
                };

                vm.bookMKS = function () {
                    MISbtn.url = 'https://popup-sandbox.herokuapp.com';
                    MISbtn.register();
                };

                vm.geolocateUser = function () {
                    $rootScope.log("Geolocating user");

                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(vm.showPosition);
                    } else {
                        $rootScope.log("Geolocation is not supported by this browser.");
                    }

                };

                vm.showPosition = function (position) {

                    vm.userLocation.lat = position.coords.latitude;
                    vm.userLocation.lon = position.coords.longitude;

                    $rootScope.log(vm.userLocation);

                };

                vm.geolocateUser();
            }
        ]
    );
});
