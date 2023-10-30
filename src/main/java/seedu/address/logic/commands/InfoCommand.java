package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Updates the shift days of an existing person in the contact list.
 */
public class InfoCommand extends Command {

    public static final String COMMAND_WORD = "info";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the details of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_SHOW_INFO_SUCCESS = "Person information displayed on window!";
    private final Index targetIndex;

    /**
     * Creates an InfoCommand to display the information of the person with the specified {@code Index}
     */
    public InfoCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return new CommandResult(MESSAGE_SHOW_INFO_SUCCESS, false, false,
                Optional.of(targetIndex.getZeroBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InfoCommand)) {
            return false;
        }

        InfoCommand e = (InfoCommand) other;
        return targetIndex.equals(e.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
