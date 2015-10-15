define(['services'], function (services) {
    services.service('HotelContentApiSrv', ['$http', 'Settings',
            function ($http, Settings) {

                var V_LANGUAGE = '{lang}';
                var V_QUERY = '{term}';

                this.apiUrl = Settings.HOTEL_CONTENT_API;
                $http.defaults.headers.get = Settings.HEADERS;
                $http.defaults.headers.post = Settings.HEADERS;
                $http.defaults.headers.delete = Settings.HEADERS;
                this.servicePaths = {
                    SEARCH_HOTELS: 'Hotel?query=*{term}*&language={lang}',
                    SEARCH_ZONES: 'Zone?query=*{term}*&language={lang}',
                    SEARCH_DESTINATIONS: 'Destination?query=*{term}*&language={lang}',
                };


                this.getDestinations = function (manageResults, manageError, language, text) {

                    $http.get(this.apiUrl + this.servicePaths.SEARCH_DESTINATIONS.replace(V_QUERY, text).replace(V_LANGUAGE, language))
                        .success(function (data) {
                            manageResults(data.elements);
                        }).error(function (data) {
                            manageError(data.error);
                        });
                };


                this.getZones = function (manageResults, manageError, language, text) {

                    $http.get(this.apiUrl + this.servicePaths.SEARCH_ZONES.replace(V_QUERY, text).replace(V_LANGUAGE, language))
                        .success(function (data) {
                            manageResults(data.elements);
                        }).error(function (data) {
                            manageError(data.error);
                        });
                };

                this.getHotels = function (manageResults, manageError, language, text) {

                    $http.get(this.apiUrl + this.servicePaths.SEARCH_HOTELS.replace(V_QUERY, text).replace(V_LANGUAGE, language))
                        .success(function (data) {
                            manageResults(data.elements);
                        }).error(function (data) {
                            manageError(data.error);
                        });
                };


            }]
    );
});
