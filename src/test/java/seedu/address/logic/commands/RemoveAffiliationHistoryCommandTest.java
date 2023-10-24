package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RemoveAffiliationHistoryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new RemoveAffiliationHistoryCommand(null));
    }

    @Test
    public void execute_validIndex_success() {
        RemoveAffiliationHistoryCommand removeAffiliationHistoryCommand = new RemoveAffiliationHistoryCommand(
                Index.fromZeroBased(0));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person expectedPerson = new PersonBuilder(model.getFilteredPersonList().get(0))
                .withAffiliationHistory().build();
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        String expectedMessage = String.format(RemoveAffiliationHistoryCommand.MESSAGE_SUCCESS,
                Messages.format(expectedPerson));
        assertCommandSuccess(removeAffiliationHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        assertCommandFailure(new RemoveAffiliationHistoryCommand(outOfBoundIndex), model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        final RemoveAffiliationHistoryCommand standardCommand = new RemoveAffiliationHistoryCommand(INDEX_FIRST_PERSON);

        // same values -> returns true
        RemoveAffiliationHistoryCommand commandWithSameValues = new RemoveAffiliationHistoryCommand(INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemoveAffiliationHistoryCommand(INDEX_SECOND_PERSON)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);

        final RemoveAffiliationHistoryCommand removeAffiliationHistoryCommand =
                new RemoveAffiliationHistoryCommand(index);

        String expected = RemoveAffiliationHistoryCommand.class.getCanonicalName() + "{index=" + index + "}";
        assertEquals(expected, removeAffiliationHistoryCommand.toString());
    }
}
