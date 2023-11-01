package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_AFFILIATION = "#friend";
    private static final List<Integer> INVALID_SHIFT_DAYS = Arrays.asList(-1, 8);
    private static final List<String> INVALID_SPECIALISATIONS = Arrays.asList(" ", "#A&E");
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final List<JsonAdaptedAffiliation> VALID_AFFILIATIONS = BENSON.getAffiliations().stream()
            .map(JsonAdaptedAffiliation::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAffiliation> VALID_AFFILIATION_HISTORY = BENSON.getAffiliationHistory()
            .stream()
            .map(JsonAdaptedAffiliation::new)
            .collect(Collectors.toList());
    private static final List<Integer> VALID_SHIFT_DAYS = new ArrayList<>();
    private static final List<String> VALID_SPECIALISATIONS = new ArrayList<>();
    private static final String VALID_RELATIONSHIP = "Brother";
    private static final JsonAdaptedNextOfKin VALID_NEXT_OF_KIN =
            new JsonAdaptedNextOfKin(VALID_NAME, VALID_PHONE, VALID_RELATIONSHIP);

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(ALICE);
        assertEquals(ALICE, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                    VALID_ROLE, VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS,
                    VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE,
                VALID_EMAIL, VALID_ROLE, VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY,
                VALID_SHIFT_DAYS, VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ROLE,
                    VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS,
                    VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null,
                VALID_EMAIL, VALID_ROLE, VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS,
                VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ROLE,
                    VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS,
                    VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE, null, VALID_ROLE, VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY,
                VALID_SHIFT_DAYS, VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ROLE,
                    VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS,
                    VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY,
                VALID_SHIFT_DAYS, VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAffiliations_throwsIllegalValueException() {
        List<JsonAdaptedAffiliation> invalidAffiliations = new ArrayList<>(VALID_AFFILIATIONS);
        invalidAffiliations.add(new JsonAdaptedAffiliation(INVALID_AFFILIATION));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE, invalidAffiliations,
                        VALID_AFFILIATION_HISTORY, VALID_SHIFT_DAYS, VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidShiftDays_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                        VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY,
                        INVALID_SHIFT_DAYS, VALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSpecialisations_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROLE,
                        VALID_AFFILIATIONS, VALID_AFFILIATION_HISTORY,
                        VALID_SHIFT_DAYS, INVALID_SPECIALISATIONS, VALID_NEXT_OF_KIN);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
