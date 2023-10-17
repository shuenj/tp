package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddAffiliationCommand.
 */
public class AddAffiliationCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        assertThrows(NullPointerException.class, () ->
                new AddAffiliationCommand(indexLastPerson, null));
        assertThrows(NullPointerException.class, () ->
                new AddAffiliationCommand(null, lastPerson.getAffiliations()));
        assertThrows(NullPointerException.class, () ->
                new AddAffiliationCommand(null, null));
    }

    @Test
    public void execute_allFieldsValid_success() {
        Person doctor = model.getFilteredPersonList().get(Index.fromZeroBased(0).getZeroBased());
        Person patient = model.getFilteredPersonList().get(Index.fromZeroBased(3).getZeroBased());

        Set<Affiliation> toAddAffiliation = new HashSet<>();
        toAddAffiliation.add(new Affiliation(patient.getName().fullName));

        AddAffiliationCommand addAffiliationCommand = new AddAffiliationCommand(
                Index.fromZeroBased(0), toAddAffiliation);


        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person expectedDoctor = new PersonBuilder().withName("Alice Pauline")
                .withRole("Doctor").withEmail("alice@example.com")
                .withPhone("94351253")
                .withAffiliations("Benson Meier", patient.getName().fullName)
                .withAffiliationHistory("Benson Meier", patient.getName().fullName).build();
        Person expectedPatient = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withEmail("cornelia@example.com").withRole("Patient")
                .withAffiliations(doctor.getName().fullName)
                .withAffiliationHistory(doctor.getName().fullName).build();
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedDoctor);
        expectedModel.setPerson(model.getFilteredPersonList().get(3), expectedPatient);
        String expectedMessage = String.format(AddAffiliationCommand.MESSAGE_ADD_AFFILIATION_SUCCESS,
                Messages.format(expectedDoctor));
        assertCommandSuccess(addAffiliationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Person patient = model.getFilteredPersonList().get(Index.fromZeroBased(3).getZeroBased());

        Set<Affiliation> toAddAffiliation = new HashSet<>();
        toAddAffiliation.add(new Affiliation(patient.getName().fullName));

        AddAffiliationCommand addAffiliationCommand = new AddAffiliationCommand(outOfBoundIndex, toAddAffiliation);

        assertCommandFailure(addAffiliationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Affiliation> toAddAffiliation = new HashSet<>();
        toAddAffiliation.add(new Affiliation(VALID_NAME_AMY));

        final AddAffiliationCommand standardCommand = new AddAffiliationCommand(INDEX_FIRST_PERSON, toAddAffiliation);

        // same values -> returns true
        Set<Affiliation> otherToAddAffiliation = new HashSet<>();
        otherToAddAffiliation.add(new Affiliation(VALID_NAME_AMY));
        AddAffiliationCommand commandWithSameValues = new AddAffiliationCommand(
                INDEX_FIRST_PERSON, otherToAddAffiliation);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddAffiliationCommand(INDEX_SECOND_PERSON, toAddAffiliation)));

        // different descriptor -> returns false
        otherToAddAffiliation.add(new Affiliation(VALID_NAME_BOB));
        assertFalse(standardCommand.equals(new AddAffiliationCommand(INDEX_FIRST_PERSON, otherToAddAffiliation)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Set<Affiliation> toAddAffiliation = new HashSet<>();
        toAddAffiliation.add(new Affiliation(VALID_NAME_AMY));

        final AddAffiliationCommand addAffiliationCommand = new AddAffiliationCommand(index, toAddAffiliation);
        String expected = AddAffiliationCommand.class.getCanonicalName() + "{index=" + index + ", affiliationListToAdd="
                + toAddAffiliation + "}";
        assertEquals(expected, addAffiliationCommand.toString());
    }

}
