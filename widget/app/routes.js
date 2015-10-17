define(['app'], function (app) {
    'use strict';
    return app.config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise('/search');

            $stateProvider
                .state('search', {
                    url: '/search',
                    views: {
                        '': {
                            controller: 'SearcherCtrl as searcherCtrl',
                            templateUrl: 'app/components/searcher/views/search.html'
                        }
                        ,'loading@search': {templateUrl: 'app/views/loading.html'}
                    }

                }).state('summary', {
                    url: '/summary',
                    views: {
                        '': {
                            controller: 'SearcherCtrl as searcherCtrl',
                            templateUrl: 'app/components/searcher/views/summary.html'
                        }
                        ,'details@summary': {templateUrl: 'app/components/searcher/views/partials/details.html'}
                        ,'loading@summary': {templateUrl: 'app/views/loading.html'}
                    }

                }).state('voucher', {
                    url: '/voucher',
                    views: {
                        '': {
                            controller: 'SearcherCtrl as searcherCtrl',
                            templateUrl: 'app/components/searcher/views/voucher.html'
                        }
                        ,'details@voucher': {templateUrl: 'app/components/searcher/views/partials/details.html'}
                        ,'loading@voucher': {templateUrl: 'app/views/loading.html'}
                    }

                });
        }]);

});


