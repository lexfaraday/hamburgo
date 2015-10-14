/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
public class Holder {

	@Size(min = 1, max = 50, message = "{javax.validation.constraints.Size.message}") 
	private String name;
	@Size(min = 1, max = 50, message = "{javax.validation.constraints.Size.message}") 
	private String surname;


}