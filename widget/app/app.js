define([
    //deps to load js files
    'angular',
    'lodash',
    'moment',
    'makeitsocial',
    'angular-ui-router',

    //filters


    //directives
    'wowInit', 'misbtn',

    //controllers
    'mainCtrl', 'searcherCtrl',

    //services
    'mockDataSrv', 'hotelApiSrv', 'hotelContentApiSrv'

], function (angular) {
    'use strict';

    var app = angular.module('app', [
        //deps in angular
        'controllers',
        'directives',
        'ui.router'
    ]);

    app.config(
        function ($locationProvider) {
            $locationProvider.html5Mode(true);
        }
    );
    
    app.run(['$rootScope', '$timeout', function ($rootScope, $timeout) {

    }]);

    return app;
});










