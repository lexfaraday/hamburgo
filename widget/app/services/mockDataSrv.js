define(['services'], function (services) {
    services.service('MockDataSrv', ['$http', 'Settings',
            function ($http, Settings) {

                this.getMockAvailData = function (manageResults, manageError) {
                    $http.get('data/Avail.json').success(function (data) {
                        manageResults(data, null);
                    }).error(function (data, status, headers, config) {
                        manageError("get mock data error " + status);
                    });
                };

                this.getMockBookingListData = function (request, manageData, manageError) {
                    $http.get('data/BookingList.json').success(function (data) {
                        manageData(request, data);
                    }).error(function (data, status, headers, config) {
                        manageError("get mock data error " + status);
                    });
                };

                this.getMockBookingDetailData = function (request, manageData, manageError) {
                    $http.get('data/BookingDetail.json').success(function (data) {
                        manageData(request, data);
                    }).error(function (data, status, headers, config) {
                        manageError("get mock data error " + status);
                    });
                };


            }]
    );
});
