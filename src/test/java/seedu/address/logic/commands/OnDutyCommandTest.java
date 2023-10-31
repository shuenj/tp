package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStaff.CARL;
import static seedu.address.testutil.TypicalStaff.ELLE;
import static seedu.address.testutil.TypicalStaff.FIONA;
import static seedu.address.testutil.TypicalStaff.getTypicalStaffAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StaffOnDutyPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class OnDutyCommandTest {

    private Model model = new ModelManager(getTypicalStaffAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        OnDutyCommand defaultOnDutyCommand = new OnDutyCommand();

        StaffOnDutyPredicate firstPredicate = new StaffOnDutyPredicate(1);
        StaffOnDutyPredicate secondPredicate = new StaffOnDutyPredicate(2);

        OnDutyCommand onDutyFirstCommand = new OnDutyCommand(firstPredicate);
        OnDutyCommand onDutySecondCommand = new OnDutyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(defaultOnDutyCommand.equals(defaultOnDutyCommand));

        // same values -> returns true
        OnDutyCommand onDutyFirstCommandCopy = new OnDutyCommand(firstPredicate);
        assertTrue(onDutyFirstCommand.equals(onDutyFirstCommandCopy));

        // different types -> returns false
        assertFalse(defaultOnDutyCommand.equals(1));

        // null -> returns false
        assertFalse(defaultOnDutyCommand.equals(null));

        // different indicated predicate -> returns false
        assertFalse(onDutyFirstCommand.equals(onDutySecondCommand));
    }

    @Test
    public void execute_multipleStaffFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(3);
        OnDutyCommand command = new OnDutyCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(2);
        OnDutyCommand onDutyCommand = new OnDutyCommand(predicate);
        String expected = OnDutyCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, onDutyCommand.toString());
    }
}
