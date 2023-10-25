package seedu.address.storage;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom JSON serializer for converting a {@link JsonAdaptedPerson} object into JSON format.
 *
 * <p>The serialization process includes converting fields such as name, phone, email, role, affiliations,
 * and affiliation history to JSON fields. Additionally, it conditionally includes the shiftDays
 * field in the JSON output based on the person's role. If the role is "Doctor" or "Nurse", the shiftDays field is
 * included; otherwise, it is excluded.
 */
public class CustomJsonAdaptedPersonSerializer extends JsonSerializer<JsonAdaptedPerson> {

    /**
     * Serializes the {@code JsonAdaptedPerson} object into JSON format.
     *
     * @param person The {@code JsonAdaptedPerson} object to be serialized.
     * @param gen The JSON generator to write the serialized data to.
     * @param serializers The serializer provider for handling serializers.
     * @throws IOException If there is an I/O error during the serialization process.
     */
    @Override
    public void serialize(JsonAdaptedPerson person, JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        gen.writeStartObject();

        gen.writeStringField("name", person.getName());
        gen.writeStringField("phone", person.getPhone());
        gen.writeStringField("email", person.getEmail());
        gen.writeStringField("role", person.getRole());

        if ("Doctor".equals(person.getRole()) || "Nurse".equals(person.getRole())) {
            gen.writeArrayFieldStart("shiftDays");
            for (Integer shiftDay : person.getShiftDays()) {
                gen.writeNumber(shiftDay);
            }
            gen.writeEndArray();
        }

        gen.writeArrayFieldStart("affiliations");
        for (JsonAdaptedAffiliation affiliation : person.getAffiliations()) {
            gen.writeObject(affiliation);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("affiliationHistory");
        for (JsonAdaptedAffiliation affiliation : person.getAffiliationHistory()) {
            gen.writeObject(affiliation);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}

