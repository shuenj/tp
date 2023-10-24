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

import seedu.address.model.person.Patient;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Staff;
import seedu.address.testutil.TypicalPatients;
import seedu.address.testutil.TypicalStaff;

public class CustomJsonAdaptedPersonSerializerTest {

    @Test
    public void serialize_staffPersonWithShiftDays_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Staff staff = TypicalStaff.ALICE;
        ShiftDays shiftDays = new ShiftDays(new HashSet<>(Arrays.asList(1, 2, 3)));
        staff.setShiftDays(shiftDays);

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(staff), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();
        assertTrue(jsonOutput.contains("shiftDays"));
        ShiftDays undoShiftDays = new ShiftDays(new HashSet<>());
        staff.setShiftDays(undoShiftDays);
    }

    @Test
    public void serialize_nonStaffPersonWithoutShiftDays_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Patient nonStaff = TypicalPatients.ALICE;

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(nonStaff), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();

        assertFalse(jsonOutput.contains("shiftDays"));
    }
}
