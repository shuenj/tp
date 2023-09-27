package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;

/**
 * Finds doctors/patients affiliated with patient/doctor.
 */
public class AffiliationCommand extends Command {

    public static final String COMMAND_WORD = "affn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds and returns the list "
            + "of people who are affiliated with the person identified by their full name. "
            + "(i.e. returns the doctors of a patient, or the patients of a doctor)\n"
            + "Parameters: [FULL NAME]\n"
            + "Example: " + COMMAND_WORD + "John Doe";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Check back later!";

    private final Name name;

    public AffiliationCommand(Name name) {
        requireNonNull(name);

        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(name.fullName);
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
        return name.equals(e.name);
    }
}
