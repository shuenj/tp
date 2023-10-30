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
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Specialisation;
import seedu.address.testutil.TypicalDoctors;
import seedu.address.testutil.TypicalNurses;
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
    public void serialize_nursePersonWithShiftDays_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Nurse nurse = TypicalNurses.ALICE;
        ShiftDays shiftDays = new ShiftDays(new HashSet<>(Arrays.asList(1, 2, 3)));
        nurse.setShiftDays(shiftDays);

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(nurse), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();
        assertTrue(jsonOutput.contains("shiftDays"));
        ShiftDays undoShiftDays = new ShiftDays(new HashSet<>());
        nurse.setShiftDays(undoShiftDays);
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

    @Test
    public void serialize_doctorPersonWithSpecialisations_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Doctor doctor = TypicalDoctors.ALICE;
        HashSet<Specialisation> specialisations = new HashSet<Specialisation>();
        specialisations.add(new Specialisation("ENT"));
        specialisations.add(new Specialisation("cardiology"));
        doctor.setSpecialisations(specialisations);

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(doctor), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();
        assertTrue(jsonOutput.contains("specialisations"));
        doctor.setSpecialisations(new HashSet<>());
    }

    @Test
    public void serialize_nursePersonWithoutSpecialisations_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Nurse nurse = TypicalNurses.ALICE;

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(nurse), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();

        assertFalse(jsonOutput.contains("specialisations"));
    }

    @Test
    public void serialize_nonStaffPersonWithoutSpecialisations_success() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        CustomJsonAdaptedPersonSerializer serializer = new CustomJsonAdaptedPersonSerializer();

        Patient nonStaff = TypicalPatients.ALICE;

        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(writer);
        SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        serializer.serialize(new JsonAdaptedPerson(nonStaff), jsonGenerator, serializerProvider);

        String jsonOutput = writer.toString();

        assertFalse(jsonOutput.contains("specialisations"));
    }
}
