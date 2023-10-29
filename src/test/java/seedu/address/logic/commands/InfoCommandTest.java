package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.InfoCommand.MESSAGE_SHOW_INFO_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code InfoCommand}.
 */
public class InfoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InfoCommand infoCommand = new InfoCommand(INDEX_FIRST_PERSON);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SHOW_INFO_SUCCESS,
                false, false, Optional.of(INDEX_FIRST_PERSON.getZeroBased()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(infoCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        InfoCommand infoCommand = new InfoCommand(outOfBoundIndex);
        assertCommandFailure(infoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        InfoCommand infoCommand = new InfoCommand(INDEX_FIRST_PERSON);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SHOW_INFO_SUCCESS,
                false, false, Optional.of(INDEX_FIRST_PERSON.getZeroBased()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(infoCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        InfoCommand infoCommand = new InfoCommand(outOfBoundIndex);
        assertCommandFailure(infoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        InfoCommand infoFirstCommand = new InfoCommand(INDEX_FIRST_PERSON);
        InfoCommand infoSecondCommand = new InfoCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(infoFirstCommand.equals(infoFirstCommand));

        // same values -> returns true
        InfoCommand infoFirstCommandCopy = new InfoCommand(INDEX_FIRST_PERSON);
        assertTrue(infoFirstCommand.equals(infoFirstCommandCopy));

        // different types -> returns false
        assertFalse(infoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(infoFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(infoFirstCommand.equals(infoSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        InfoCommand infoCommand = new InfoCommand(targetIndex);
        String expected = InfoCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, infoCommand.toString());
    }
}
