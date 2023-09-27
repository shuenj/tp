package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AffiliationCommand.
 */
public class AffiliationCommandTest {



    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Name name = new Name("Jill Strongman");

        assertCommandFailure(new AffiliationCommand(name), model, name.fullName);
    }

    @Test
    public void equals() {
        final AffiliationCommand standardCommand = new AffiliationCommand(new Name(VALID_NAME_AMY));

        // same values -> returns true
        final AffiliationCommand commandWithSameValues = new AffiliationCommand(new Name(VALID_NAME_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        assertFalse(standardCommand.equals(new AffiliationCommand(new Name(VALID_NAME_BOB))));
    }
}
