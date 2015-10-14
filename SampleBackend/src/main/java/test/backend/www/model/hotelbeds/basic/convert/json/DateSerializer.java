/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.convert.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import test.backend.www.model.hotelbeds.basic.util.AssignUtils;

public class DateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(final LocalDate date, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        final String dateString = date.format(AssignUtils.REST_FORMATTER);
        generator.writeString(dateString);
    }
}