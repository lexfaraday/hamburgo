/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class Card {

	@Size(min = 1, max = 10, message = "{javax.validation.constraints.Size.message}") 
	private String type;
	@Size(min = 0, max = 25, message = "{javax.validation.constraints.Size.message}") 
	private String content;


}