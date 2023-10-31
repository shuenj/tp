package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PatientBuilder;

public class NextOfKinCommandTest {

    private Model model = new ModelManager(getTypicalPatientsAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(null, VALID_NEXT_OF_KIN_AMY));
        assertThrows(NullPointerException.class, () -> new NextOfKinCommand(null, null));
    }

    @Test
    public void execute_allFieldsValid_success() {
        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(
                Index.fromZeroBased(0), VALID_NEXT_OF_KIN_AMY);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Patient patient = (Patient) expectedModel.getFilteredPersonList().get(0);
        Patient expectedPatient = new PatientBuilder(patient).build();
        expectedPatient.setNextOfKin(VALID_NEXT_OF_KIN_AMY);

        String expectedMessage = String.format(NextOfKinCommand.MESSAGE_SUCCESS,
                Messages.format(expectedPatient));
        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullNextOfKin_success() {
        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(
                Index.fromZeroBased(0), null);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Patient patient = (Patient) expectedModel.getFilteredPersonList().get(0);
        Patient expectedPatient = new PatientBuilder(patient).build();
        expectedPatient.clearNextOfKins();

        String expectedMessage = String.format(NextOfKinCommand.MESSAGE_SUCCESS,
                Messages.format(expectedPatient));
        assertCommandSuccess(nextOfKinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(Index.fromZeroBased(0),
                VALID_NEXT_OF_KIN_AMY);

        NextOfKinCommand nextOfKinCommandDifferentIndex = new NextOfKinCommand(Index.fromZeroBased(1),
                VALID_NEXT_OF_KIN_AMY);

        NextOfKinCommand nextOfKinCommandDifferentNextOfKin =
                new NextOfKinCommand(Index.fromZeroBased(0), VALID_NEXT_OF_KIN_BOB);

        // same object -> returns true
        assertTrue(nextOfKinCommand.equals(nextOfKinCommand));

        // same values -> returns true
        NextOfKinCommand nextOfKinCommandSame = new NextOfKinCommand(Index.fromZeroBased(0),
                VALID_NEXT_OF_KIN_AMY);
        assertTrue(nextOfKinCommand.equals(nextOfKinCommandSame));

        // different types -> returns false
        assertFalse(nextOfKinCommand.equals(1));

        // null -> returns false
        assertFalse(nextOfKinCommand.equals(null));

        // different index -> returns false
        assertFalse(nextOfKinCommand.equals(nextOfKinCommandDifferentIndex));

        // different next of kin -> returns false
        assertFalse(nextOfKinCommand.equals(nextOfKinCommandDifferentNextOfKin));
    }

    @Test
    public void toStringMethod() {
        NextOfKinCommand nextOfKinCommand = new NextOfKinCommand(Index.fromZeroBased(0),
                VALID_NEXT_OF_KIN_AMY);
        String expected = NextOfKinCommand.class.getCanonicalName() + "{index=" + Index.fromZeroBased(0)
                + ", " + "nextOfKin=" + VALID_NEXT_OF_KIN_AMY + "}";
        assertEquals(expected, nextOfKinCommand.toString());
    }
}
