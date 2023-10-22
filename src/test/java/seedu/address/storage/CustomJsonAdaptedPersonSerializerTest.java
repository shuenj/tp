package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.ShiftDays;
import seedu.address.testutil.TypicalDoctors;
import seedu.address.testutil.TypicalPatients;

public class CustomJsonAdaptedPersonSerializerTest {

    @Test
    public void serialize_doctorPersonWithShiftDays_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Doctor doctor = TypicalDoctors.ALICE;
        ShiftDays shiftDays = new ShiftDays(new HashSet<>(Arrays.asList(1, 2, 3)));
        doctor.setShiftDays(shiftDays);

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(doctor), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();
        assertTrue(jsonOutput.contains("shiftDays"));
        ShiftDays undoShiftDays = new ShiftDays(new HashSet<>());
        doctor.setShiftDays(undoShiftDays);
    }

    @Test
    public void serialize_nonDoctorPersonWithoutShiftDays_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Patient nonDoctor = TypicalPatients.ALICE;

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(nonDoctor), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();

        assertFalse(jsonOutput.contains("shiftDays"));
    }
}
