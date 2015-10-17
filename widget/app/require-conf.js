require.config({

    baseUrl: "libs",
    // alias libraries paths
    paths: {
        'app': '../app/app',
        'bootstrap': '../app/bootstrap',
        'routes': '../app/routes',
        'constants': '../app/constants',


        //ANGULAR DIRECTIVES
        'directives': '../app/directives/directives',
        'wowInit': '../app/directives/wowInit',
        'misbtn': '../app/directives/misbtn',

        //ANGULAR CONTROLLERS
        'controllers': '../app/controllers/controllers',
        'mainCtrl': '../app/controllers/mainCtrl',
        'searcherCtrl': '../app/components/searcher/searcherCtrl',

        //ANGULAR SERVICES
        'services': '../app/services/services',
        'hotelApiSrv': '../app/services/hotelApiSrv',
        'hotelContentApiSrv': '../app/services/hotelContentApiSrv',
        'mockDataSrv': '../app/services/mockDataSrv',

        //EXTERNAL LIBS
        'domReady': 'requirejs-domready/domReady',
        'moment': 'moment/min/moment.min',
        'lodash': 'lodash/lodash',
        'wow': 'wow/dist/wow.min',
        'makeitsocial': 'makeitsocial/dist/mis-button',

        'jquery': 'jquery/jquery',

        'angular': 'angular/angular',
        'angular-ui-router': 'angular-ui-router/release/angular-ui-router'

    },

    // angular does not support AMD out of the box, put it in a shim
    shim: {
        'angular': {
            exports: 'angular',
            deps: ['jquery']
        },
        'angular-ui-router': {
            deps: ['angular']
        }
    },

    // kick start application
    deps: ['bootstrap']
});
