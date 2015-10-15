/**
 * bootstraps angular onto the window.document node
 */
define([
    'require',
    'angular',
    'jquery',
    'app',
    'routes',
    'constants'
], function (require, angular) {
    'use strict';

    require(['domReady!'], function (document) {
        angular.bootstrap(document, ['app']);
    });
});
