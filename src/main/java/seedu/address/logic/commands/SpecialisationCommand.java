package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialisation;

/**
 * Modifies the specialisations of an existing doctor in the contact list.
 */
public class SpecialisationCommand extends Command {

    public static final String COMMAND_WORD = "spec";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modify the specialisation to the person identified"
        + " by the index number used in the displayed person list. "
        + "Clear the specialisation by not providing any specialisation.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "SPEC (must be a valid specialisation)\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "ENT, Orthopaedic";

    public static final String MESSAGE_SUCCESS = "Specialisation modified!";
    private static Logger logger = Logger.getLogger("Specialisation Command Logger");
    private final Index index;
    private final Set<Specialisation> specialisations;

    /**
     * Creates a SpecialisationCommand to modify the specified {@code Specialisation}
     * @param index of the person in the filtered person list to modify
     * @param specialisations of the person to be modified to
     */
    public SpecialisationCommand(Index index, Set<Specialisation> specialisations) {
        requireNonNull(index);
        requireNonNull(specialisations);

        this.index = index;
        this.specialisations = specialisations;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Error: Invalid index");
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToModify = lastShownList.get(index.getZeroBased());
        if (!(personToModify instanceof Doctor)) {
            logger.log(Level.WARNING, "Error: Invalid role");
            throw new CommandException(Messages.MESSAGE_INVALID_ROLE);
        }
        Doctor modifiedPerson = (Doctor) personToModify;
        modifiedPerson.setSpecialisations(specialisations);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(modifiedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SpecialisationCommand)) {
            return false;
        }

        // state check
        SpecialisationCommand e = (SpecialisationCommand) other;
        return index.equals(e.index)
                && specialisations.equals(e.specialisations);
    }
}
