package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CAT;
import static seedu.address.logic.parser.ParserUtil.parseAffiliations;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.NurseBuilder;
import seedu.address.testutil.PatientBuilder;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidRole_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null role
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid roles
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only

        // valid roles
        assertTrue(Role.isValidRole("Nurse"));
        assertTrue(Role.isValidRole("Doctor"));
        assertTrue(Role.isValidRole("Patient"));
        assertTrue(Role.isValidRole("DoCtOR")); // mixed case role
        assertTrue(Role.isValidRole("nUrSe")); // mixed case role
        assertTrue(Role.isValidRole("PaTIEnt")); // mixed case role

    }

    @Test
    public void equals() {
        Role role = new Role("Doctor");

        // same values -> returns true
        assertTrue(role.equals(new Role("Doctor")));

        // same object -> returns true
        assertTrue(role.equals(role));

        // null -> returns false
        assertFalse(role.equals(null));

        // different types -> returns false
        assertFalse(role.equals(5.0f));

        // different values -> returns false
        assertFalse(role.equals(new Role("Patient")));
    }

    @Test
    public void generate_staffRole_correctTypeReturned() {
        Doctor doctor = new DoctorBuilder().build();
        Role doctorRole = new Role(VALID_ROLE_AMY);
        try {
            assertEquals(doctor.getClass(),
                    doctorRole.generatePerson(new Name(VALID_NAME_AMY),
                            new Phone(VALID_PHONE_AMY),
                            new Email(VALID_EMAIL_AMY),
                            parseAffiliations(new ArrayList<>())).getClass());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }

        Nurse nurse = new NurseBuilder().build();
        Role nurseRole = new Role(VALID_ROLE_CAT);
        try {
            assertEquals(nurse.getClass(),
                    nurseRole.generatePerson(new Name(VALID_NAME_CAT),
                            new Phone(VALID_PHONE_CAT),
                            new Email(VALID_EMAIL_CAT),
                            parseAffiliations(new ArrayList<>())).getClass());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void generate_patientRole_correctTypeReturned() {
        Patient patient = new PatientBuilder().build();
        Role role = new Role(VALID_ROLE_BOB);
        try {
            assertEquals(patient.getClass(),
                    role.generatePerson(new Name(VALID_NAME_BOB),
                            new Phone(VALID_PHONE_BOB),
                            new Email(VALID_EMAIL_BOB),
                            parseAffiliations(new ArrayList<>())).getClass());
        } catch (Exception e) {
            fail(); // the test should not reach this line
        }
    }
}
