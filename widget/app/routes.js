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
                        },
                        'tabs@search': {templateUrl: 'app/components/searcher/views/partials/tabs.html'}
                    }

                });
        }]);

});


