/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.model.Bookings;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class BookingListRS extends AbstractGenericResponse {

	private Bookings bookings;


}