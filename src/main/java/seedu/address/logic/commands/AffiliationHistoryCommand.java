package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.NameMatchesAffiliationPredicate;
import seedu.address.model.person.Person;

/**
 * Finds doctors/patients affiliated with patient/doctor.
 */
public class AffiliationHistoryCommand extends Command {

    public static final String COMMAND_WORD = "affnh";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and returns the list of people who "
        + "are affiliated or once affiliated with the person identified by the index number shown in the  "
        + "displayed person list. "
        + "(i.e. returns the doctors of a patient, or the patients of a doctor)\n"
        + "The index must be a positive integer 1, 2, 3, …\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 2";

    private final Index index;

    /**
     * Creates an AffiliationCommand to return the affiliation history for the specified {@code Index}
     */
    public AffiliationHistoryCommand(Index index) {
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

        Person personToGetAffiliationsOf = lastShownList.get(index.getZeroBased());
        Set<Affiliation> affiliationHistory = personToGetAffiliationsOf.getAffiliationHistory();

        model.updateFilteredPersonList(new NameMatchesAffiliationPredicate(affiliationHistory));

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AffiliationHistoryCommand)) {
            return false;
        }

        AffiliationHistoryCommand e = (AffiliationHistoryCommand) other;
        return index.equals(e.index);
    }

}
