package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.person.ShiftDays;

public class ShiftCommand extends Command {

    public static final String COMMAND_WORD = "shift";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the shift days of the "
            + "person identified with the given index number.\n"
            + "The index must be a positive integer 1, 2, 3, …\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "SHIFT_DAYS (from 1, Monday to 7, Sunday, typed with no spaces)"
            + "Example: " + COMMAND_WORD + " "
            + "2" + " "
            + "1346";

    public static final String MESSAGE_SUCCESS = "Shift days added!";

    private final Index index;

    private final Set<Integer> shiftDayNumbers;

    /**
     * Creates an ShiftCommand to set the shift dates for the specified {@code Index}
     */
    public ShiftCommand(Index index, Set<Integer> shiftDayNumbers) {
        requireNonNull(index);
        requireNonNull(shiftDayNumbers);

        this.index = index;
        this.shiftDayNumbers = shiftDayNumbers;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToSetShiftDatesOf = lastShownList.get(index.getZeroBased());
        if (!(personToSetShiftDatesOf instanceof Doctor)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROLE);
        }
        Doctor doctorToSetShiftDatesOf = (Doctor) personToSetShiftDatesOf; // checked cast
        ShiftDays shiftDays = new ShiftDays(shiftDayNumbers);
        doctorToSetShiftDatesOf.setShiftDays(shiftDays);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShiftCommand)) {
            return false;
        }

        ShiftCommand e = (ShiftCommand) other;
        return index.equals(e.index) && shiftDayNumbers.equals(e.shiftDayNumbers);
    }

}
