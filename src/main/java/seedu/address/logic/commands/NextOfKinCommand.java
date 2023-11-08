package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Modifies the specialisations of an existing doctor in the contact list.
 */
public class NextOfKinCommand extends Command {

    public static final String COMMAND_WORD = "nok";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modify the Next-of-Kin of the person identified"
            + " by the index number used in the displayed person list.\n"
            + "Clear the Next-of-Kin by not providing any parameters.\n"
            + "Either all of the 3 unique parameter are specified or none of the parameter is specified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME " + "]"
            + "[" + PREFIX_PHONE + "PHONE " + "]"
            + "[" + PREFIX_RELATIONSHIP + "RELATIONSHIP " + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_RELATIONSHIP + "Spouse";

    public static final String MESSAGE_SUCCESS = "Next of Kin updated!\n%1$s";
    private static Logger logger = Logger.getLogger("NextOfKin Command Logger");
    private final Index index;
    private final NextOfKin nextOfKin;

    /**
     * Creates a NextOfKinCommand to modify the specified {@code NextOfKin}
     * @param index of the person in the filtered person list to modify
     * @param nextOfKin of the person to be modified to
     */
    public NextOfKinCommand(Index index, NextOfKin nextOfKin) {
        requireNonNull(index);
        this.index = index;
        this.nextOfKin = nextOfKin;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToModify = lastShownList.get(index.getZeroBased());
        if (!(personToModify instanceof Patient)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROLE);
        }
        Patient modifiedPerson = (Patient) personToModify;
        if (nextOfKin == null) {
            modifiedPerson.clearNextOfKins();
        } else {
            modifiedPerson.setNextOfKin(nextOfKin);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(modifiedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NextOfKinCommand)) {
            return false;
        }

        // state check
        NextOfKinCommand e = (NextOfKinCommand) other;
        if (nextOfKin == null) {
            return index.equals(e.index) && (e.nextOfKin == null);
        } else {
            return index.equals(e.index) && nextOfKin.equals(e.nextOfKin);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("nextOfKin", nextOfKin)
                .toString();
    }
}
