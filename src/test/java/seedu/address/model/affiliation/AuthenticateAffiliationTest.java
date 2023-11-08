package seedu.address.model.affiliation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.getTypicalDoctorAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.affiliation.exceptions.AffiliationPersonNotFoundException;
import seedu.address.model.affiliation.exceptions.SamePersonAffiliationException;
import seedu.address.model.affiliation.exceptions.SameRoleAffiliationException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.NurseBuilder;
import seedu.address.testutil.PersonBuilder;

public class AuthenticateAffiliationTest {

    private final Model personModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model doctorModel = new ModelManager(getTypicalDoctorAddressBook(), new UserPrefs());

    @Test
    public void check_withAffiliationPersonNotExist_affiliationPersonNotFoundException() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_ALICE)
                .withRole(VALID_ROLE_BOB)
                .build();

        Person editedPerson = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_AFFILIATION_BOB)
                .withAffiliationHistory(VALID_AFFILIATION_BOB)
                .build();
        assertThrows(AffiliationPersonNotFoundException.class, () ->
                AuthenticateAffiliation.check(editedPerson.getAffiliations(), person, editedPerson, personModel));
    }

    @Test
    public void check_withAffiliationPersonSameAsPersonAdding_samePersonAffiliationException() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_ALICE)
                .withRole(VALID_ROLE_BOB)
                .build();

        Person editedPerson = new PersonBuilder()
                .withName(VALID_NAME_ALICE)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .withAffiliationHistory(VALID_NAME_ALICE)
                .build();

        assertThrows(SamePersonAffiliationException.class, () ->
                AuthenticateAffiliation.check(editedPerson.getAffiliations(), person, editedPerson, personModel));
    }

    @Test
    public void check_withAffiliationPersonSameRoleAsPersonAdding_sameRoleAffiliationException() {
        Doctor doctor = new DoctorBuilder()
                .withName(VALID_NAME_BOB).build();

        Doctor editedDoctor = new DoctorBuilder()
                .withName(VALID_NAME_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .withAffiliationHistory(VALID_NAME_ALICE)
                .build();
        assertThrows(SameRoleAffiliationException.class, () ->
                AuthenticateAffiliation.check(editedDoctor.getAffiliations(), doctor, editedDoctor, doctorModel));

        Nurse nurse = new NurseBuilder()
                .withName(VALID_NAME_BOB).build();

        Nurse editedNurse = new NurseBuilder()
                .withName(VALID_NAME_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .withAffiliationHistory(VALID_NAME_ALICE)
                .build();
        assertThrows(SameRoleAffiliationException.class, () ->
                AuthenticateAffiliation.check(editedNurse.getAffiliations(), nurse, editedNurse, doctorModel));
    }

    @Test
    public void check_validAffiliation_returnsTrue() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_BOB)
                .withRole(VALID_ROLE_BOB).build();

        Person editedPerson = new PersonBuilder()
                .withName(VALID_NAME_BOB)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .withAffiliationHistory(VALID_NAME_ALICE)
                .build();
        try {
            assertTrue(AuthenticateAffiliation.check(editedPerson.getAffiliations(),
                    person, editedPerson, personModel));
        } catch (CommandException ce) {
            fail();
        }
    }
}
