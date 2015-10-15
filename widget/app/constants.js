define(['app'], function (app) {
    'use strict';

    app.constant('Settings', {
        //Hotelbeds API's
        HOTEL_API: 'http://testapi.hotelbeds.com/hotel-api/',
        HOTEL_NOTIFY_API: "http://testapi.hotelbeds.com/hotel-notify/",
        HOTEL_CONTENT_API: "http://testapi.hotelbeds.com/hotel-content-api/search/",

        API_DATE_FORMAT: 'yyyy-MM-dd',
        WEB_DATE_FORMAT: 'dd/MM/yyyy',
        HEADERS: {
            'Api-Key': 'zffxus8c7rzfabxp8rgkgt27',
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        VERSION: '{version}',
        V_DEFAULT: '1.0',
        BOOKING_ID: '{bookingId}',
        HOTEL_API_PATHS: {
            AVAILABILITY: '{version}/hotels',
            BOOKING_LIST: '{version}/bookings',
            BOOKING_CANCEL: '{version}/bookings/{bookingId}',
            BOOKING_DETAIL: '{version}/bookings/{bookingId}',
            BOOKING_CONFIRM: '{version}/bookings'
        }
    });

});
