/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.annotation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import test.backend.www.model.hotelbeds.basic.messages.BookingListRQ;

public class ValidBookingListPaginationValidator implements ConstraintValidator<ValidBookingListPagination, Object> {

    private long maxBookingsRange;

    @Override
    public void initialize(ValidBookingListPagination constraintAnnotation) {
        maxBookingsRange = constraintAnnotation.maxBookingsRange();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!(value instanceof BookingListRQ)) {
            throw new IllegalArgumentException("Expected a parameter of type XMLBookingListRQ or JSONBookingListRQ");
        }
        Integer from = null;
        Integer to = null;
        if (value instanceof BookingListRQ) {
            from = ((BookingListRQ) value).getFrom();
            to = ((BookingListRQ) value).getTo();
        }
        context.disableDefaultConstraintViolation();
        if (from <= 0) {
            context.buildConstraintViolationWithTemplate(
                "{com.hotelbeds.distribution.hotel_api_webapp.webapp.internal.messages.BookingListRQ.pagination.minimun.message}")
                .addConstraintViolation();
            return false;
        } else if (from.compareTo(to) > 0) {
            context.buildConstraintViolationWithTemplate(
                "{com.hotelbeds.distribution.hotel_api_webapp.webapp.internal.messages.BookingListRQ.pagination.before.message}")
                .addConstraintViolation();
            return false;
        } else if (!isValidBookingsRange(from, to)) {
            context.buildConstraintViolationWithTemplate(
                "{com.hotelbeds.distribution.hotel_api_webapp.webapp.internal.messages.BookingListRQ.pagination.range.message}")
                .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isValidBookingsRange(Integer from, Integer to) {
        final long bookings = to - from + 1; //from and to are inclusive
        if (bookings > maxBookingsRange) {
            return false;
        }
        return true;
    }
}
