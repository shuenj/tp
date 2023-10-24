package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.affiliation.AffiliationModifier;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Clears the affiliation history of an existing person in the contact list.
 */
public class ClearAffiliationHistoryCommand extends Command {

    public static final String COMMAND_WORD = "clearah";
    public static final String MESSAGE_SUCCESS = "Affiliation history has been cleared!\n%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear the affiliation history of the person "
            + "identified by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 2";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to remove affiliation history.
     */
    public ClearAffiliationHistoryCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToClearAffiliationHistory = lastShownList.get(index.getZeroBased());
        AffiliationModifier.removeAffiliationHistory(personToClearAffiliationHistory.getAffiliationHistory(),
                personToClearAffiliationHistory, model);
        personToClearAffiliationHistory.clearAffiliationHistory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(personToClearAffiliationHistory)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearAffiliationHistoryCommand)) {
            return false;
        }

        ClearAffiliationHistoryCommand otherClearAffiliationHistoryCommand = (ClearAffiliationHistoryCommand) other;
        return index.equals(otherClearAffiliationHistoryCommand.index);
    }
}
