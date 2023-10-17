package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AFFILIATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.affiliation.AffiliationModifier;
import seedu.address.model.affiliation.AuthenticateAffiliation;
import seedu.address.model.person.Person;

/**
 * Add affiliations to an existing person in the contact list.
 */
public class AddAffiliationCommand extends Command {

    public static final String COMMAND_WORD = "addaffn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add affiliation to the person identified "
            + "by the index number used in the displayed person list. "
            + "Input affiliation will be added on existing affiliation.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_AFFILIATION + "AFFILIATION "
            + "[" + PREFIX_AFFILIATION + "AFFILIATION]..."
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AFFILIATION + "John Doe "
            + PREFIX_AFFILIATION + "Amy";

    public static final String MESSAGE_ADD_AFFILIATION_SUCCESS = "Added Affiliation: %1$s";
    public static final String MESSAGE_NO_AFFILIATION_INPUT = "At least one affiliation must be provided.";
    private final Index index;

    private final Set<Affiliation> affiliationListToAdd;

    /**
     * @param index of the person in the filtered person list to edit
     * @param affiliationListToAdd the additional affiliations to add.
     */
    public AddAffiliationCommand(Index index, Set<Affiliation> affiliationListToAdd) {
        requireNonNull(index);
        requireNonNull(affiliationListToAdd);

        this.index = index;
        this.affiliationListToAdd = affiliationListToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddAffiliation = lastShownList.get(index.getZeroBased());
        AuthenticateAffiliation.check(this.affiliationListToAdd, personToAddAffiliation, model);

        // Add new affiliations
        personToAddAffiliation.getAffiliations().addAll(this.affiliationListToAdd);
        personToAddAffiliation.getAffiliationHistory().addAll(this.affiliationListToAdd);

        // Add affiliation to the other affiliated person
        AffiliationModifier.addAffiliations(this.affiliationListToAdd, personToAddAffiliation, model);
        AffiliationModifier.addAffiliationHistory(this.affiliationListToAdd, personToAddAffiliation, model);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_AFFILIATION_SUCCESS,
                Messages.format(personToAddAffiliation)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAffiliationCommand)) {
            return false;
        }

        AddAffiliationCommand otherAddAffiliationCommand = (AddAffiliationCommand) other;
        return index.equals(otherAddAffiliationCommand.index)
                && affiliationListToAdd.equals(otherAddAffiliationCommand.affiliationListToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("affiliationListToAdd", affiliationListToAdd)
                .toString();
    }
}
