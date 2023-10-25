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
 * Finds staff/patients affiliated with patient/staff.
 */
public class AffiliationCommand extends Command {

    public static final String COMMAND_WORD = "affn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and returns the list of people who are "
        + "affiliated with the person identified by the index number shown in the displayed person list. "
        + "(i.e. returns the staff of a patient, or the patients of a staff)\n"
        + "The index must be a positive integer 1, 2, 3, …\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 2";

    private final Index index;

    /**
     * Creates an AffiliationCommand to return the affiliation for the specified {@code Index}
     */
    public AffiliationCommand(Index index) {
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
        Set<Affiliation> affiliations = personToGetAffiliationsOf.getAffiliations();

        model.updateFilteredPersonList(new NameMatchesAffiliationPredicate(affiliations));

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AffiliationCommand)) {
            return false;
        }

        AffiliationCommand e = (AffiliationCommand) other;
        return index.equals(e.index);
    }

}
