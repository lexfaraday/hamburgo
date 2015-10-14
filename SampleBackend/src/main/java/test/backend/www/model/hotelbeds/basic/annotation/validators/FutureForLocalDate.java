/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.annotation.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The annotated element must be a date in the future. Now is defined as the current time according to the virtual machine The calendar used if the
 * compared type is of type <code>Calendar</code> is the calendar based on the current timezone and the current locale.
 * <p/>
 * Supported types are:
 * <ul>
 * <li><code>java.util.LocalDateTime</code></li>
 * </ul>
 * <p/>
 * <code>null</code> elements are considered valid.
 *
 */
@Target(
{ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy =
{ FutureForLocalDateValidator.class })
public @interface FutureForLocalDate
{
  String message() default "{javax.validation.constraints.Future.message}";

  Class<?>[] groups() default
  {};

  Class<? extends Payload>[] payload() default
  {};

  /**
   * Defines several <code>@FutureForLocalDate</code> annotations on the same element
   *
   * @see FutureForLocalDate
   *
   */
  @Target(
  { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
  @Retention(RUNTIME)
  @Documented
  @interface List
  {
    FutureForLocalDate[] value();
  }
}
