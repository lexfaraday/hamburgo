/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.annotation.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelbedsCustomerType;
import test.backend.www.model.hotelbeds.basic.model.Occupancy;
import test.backend.www.model.hotelbeds.basic.model.Pax;

public class ValidOccupanciesValidator implements ConstraintValidator<ValidOccupancies, List<Occupancy>> {

    private int maxRooms;

    @Override
    public void initialize(final ValidOccupancies constraintAnnotation) {
        maxRooms = constraintAnnotation.maxRooms();
    }

    @Override
    public boolean isValid(final List<Occupancy> value, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value != null && !value.isEmpty()) {
            int rooms = 0;
            for (final Occupancy occupancy : value) {
                rooms += occupancy.getRooms();
                int childrenByPax = 0;
                int adultsByPax = 0;
                if (occupancy.getPaxes() != null) {
                    for (final Pax pax : occupancy.getPaxes()) {
                        if (HotelbedsCustomerType.CH.equals(pax.getType())) {
                            childrenByPax++;
                            if (pax.getAge() == null) {
                                context.buildConstraintViolationWithTemplate(
                                    "{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Occupancy.children.ages.message}")
                                    .addConstraintViolation();
                                return false;
                            }
                        } else if (HotelbedsCustomerType.AD.equals(pax.getType())) {
                            adultsByPax++;
                        }
                    }
                }
                if (occupancy.getChildren() != null
                    && ((occupancy.getChildren() == 0 && childrenByPax > 0) || (occupancy.getChildren() > 0 && !occupancy.getChildren().equals(
                        childrenByPax)))) {
                    context.buildConstraintViolationWithTemplate(
                        "{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Occupancy.children.number.message}").addConstraintViolation();
                    return false;
                }
                if (occupancy.getAdults() != null && adultsByPax > occupancy.getAdults()) {
                    context.buildConstraintViolationWithTemplate(
                        "{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Occupancy.adults.number.message}").addConstraintViolation();
                    return false;

                }

            }

            if (rooms > maxRooms) { // [API-1329]
                context.buildConstraintViolationWithTemplate(
                    "{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Occupancy.rooms.number.message}").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
