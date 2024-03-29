/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.messages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.model.Hotel;
import test.backend.www.model.hotelbeds.basic.model.Source;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class CheckRateRS extends AbstractGenericResponse {

	private List<String> providerDetails;
	private Hotel hotel;
	private Source source;


}