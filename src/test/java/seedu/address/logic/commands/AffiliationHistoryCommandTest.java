package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.NameMatchesAffiliationPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AffiliationHistoryCommand.
 */
public class AffiliationHistoryCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameMatchesAffiliationPredicate predicate = new NameMatchesAffiliationPredicate(
                Set.of(new Affiliation("Benson Meier")));
        AffiliationHistoryCommand command = new AffiliationHistoryCommand(Index.fromOneBased(1));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final AffiliationHistoryCommand standardCommand = new AffiliationHistoryCommand(Index.fromOneBased(1));

        // same values -> returns true
        final AffiliationHistoryCommand commandWithSameValues = new AffiliationHistoryCommand(Index.fromOneBased(1));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        assertFalse(standardCommand.equals(new AffiliationHistoryCommand(Index.fromOneBased(2))));
    }

}
