package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.StaffOnDutyPredicate;

/**
 * Finds and lists all staff members in contact list who are on duty for the day.
 */
public class OnDutyCommand extends Command {

    public static final String COMMAND_WORD = "onduty";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all staff members who are currently on duty "
            + "and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    private final StaffOnDutyPredicate predicate;

    /**
     * Constructs a {@code OnDutyCommand}. Predicate instantiated with one that targets the current day of the week.
     */
    public OnDutyCommand() {
        this.predicate = new StaffOnDutyPredicate();
    }

    /**
     * Constructs a {@code OnDutyCommand} with a {@code StaffOnDutyPredicate}.
     */
    public OnDutyCommand(StaffOnDutyPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OnDutyCommand)) {
            return false;
        }

        OnDutyCommand otherOnDutyCommand = (OnDutyCommand) other;
        return predicate.equals(otherOnDutyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
