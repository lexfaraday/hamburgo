define(['services'], function (services) {
    services.service('HotelApiSrv',
        ['$http', 'Settings', function ($http, Settings) {

            $http.defaults.headers.get = Settings.HEADERS;
            $http.defaults.headers.post = Settings.HEADERS;
            $http.defaults.headers.delete = Settings.HEADERS;


            var pathFor = function (operation, request) {
                var path = "";
                switch (operation) {
                    case 'avail':
                        path = Settings.HOTEL_API_PATHS.AVAILABILITY;
                        break;
                    case 'list':
                        path = Settings.HOTEL_API_PATHS.BOOKING_LIST;
                        break;
                    case 'detail':
                        path = Settings.HOTEL_API_PATHS.BOOKING_DETAIL;
                        break;
                    case 'confirm':
                        path = Settings.HOTEL_API_PATHS.BOOKING_CONFIRM;
                        break;
                    case 'cancel':
                        path = Settings.HOTEL_API_PATHS.BOOKING_CANCEL;
                        break;
                    default:
                        path = "";
                }

                return Settings.HOST + path.replace(Settings.VERSION, request.version ? request.version : Settings.V_DEFAULT);
                ;
            };

            this.availability = function (request) {
                return $http.post(pathFor('avail', request));
            };

            this.list = function (request) {
                return $http.get(pathFor('list', request));
            };

            this.detail = function (request) {
                return $http.get(pathFor('detail', request).replace(Settings.BOOKING_ID, request.bookingId));
            };

            this.confirm = function (request) {
                return $http.post(pathFor('confirm', request));
            }

            this.cancel = function (request) {
                return $http.delete(pathFor('cancel', request).replace(Settings.BOOKING_ID, request.bookingId));
            };

        }]
    );
});
