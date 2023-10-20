package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalDoctors.getTypicalDoctorsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class ShiftCommandTest {

    private Model model = new ModelManager(getTypicalDoctorsAddressBook(), new UserPrefs());

    @Test
    public void execute_roleIsDoctor_success() throws CommandException {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        Set<Integer> typicalShiftDaysString = new HashSet<>();
        typicalShiftDaysString.add(1);
        typicalShiftDaysString.add(2);
        typicalShiftDaysString.add(7);

        DoctorBuilder personInList = new DoctorBuilder((Doctor) lastPerson);
        Doctor shiftDaysChangedPerson = personInList.withShiftDays(typicalShiftDaysString).build();

        ShiftCommand shiftCommand = new ShiftCommand(indexLastPerson, typicalShiftDaysString);
        String expectedMessage = ShiftCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, shiftDaysChangedPerson);
        assertCommandSuccess(shiftCommand, model, expectedMessage, expectedModel);
        ShiftCommand unmodifyShiftCommand = new ShiftCommand(indexLastPerson, new HashSet<>());
        unmodifyShiftCommand.execute(model);
    }

    @Test
    public void execute_roleIsPatient_failure() {
        Patient patient = new PatientBuilder().build();

        Set<Integer> typicalShiftDaysString = new HashSet<>();
        typicalShiftDaysString.add(1);
        typicalShiftDaysString.add(2);
        typicalShiftDaysString.add(7);

        ShiftCommand shiftCommand = new ShiftCommand(INDEX_FIRST_PERSON, typicalShiftDaysString);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Index indexFirstPerson = Index.fromOneBased(1);
        Person firstPerson = expectedModel.getFilteredPersonList().get(indexFirstPerson.getZeroBased());
        expectedModel.setPerson(firstPerson, patient);

        assertCommandFailure(shiftCommand, expectedModel, Messages.MESSAGE_INVALID_ROLE);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Integer> typicalShiftDaysString = new HashSet<>();
        typicalShiftDaysString.add(1);
        typicalShiftDaysString.add(2);
        typicalShiftDaysString.add(7);
        ShiftCommand shiftCommand = new ShiftCommand(outOfBoundIndex, typicalShiftDaysString);
        assertCommandFailure(shiftCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of contact list
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        Set<Integer> typicalShiftDaysString = new HashSet<>();
        typicalShiftDaysString.add(1);
        typicalShiftDaysString.add(2);
        typicalShiftDaysString.add(7);
        ShiftCommand shiftCommand = new ShiftCommand(outOfBoundIndex, typicalShiftDaysString);
        assertCommandFailure(shiftCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    void equals() {
        Set<Integer> typicalShiftDaysString = new HashSet<>();
        typicalShiftDaysString.add(1);
        typicalShiftDaysString.add(2);
        typicalShiftDaysString.add(7);
        final ShiftCommand standardCommand = new ShiftCommand(Index.fromOneBased(1),
                typicalShiftDaysString);

        // same values -> returns true
        final ShiftCommand commandWithSameValues = new ShiftCommand(Index.fromOneBased(1),
                typicalShiftDaysString);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        assertFalse(standardCommand.equals(new ShiftCommand(Index.fromOneBased(2),
                typicalShiftDaysString)));
    }
}
