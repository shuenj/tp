package seedu.address.model.affiliation.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

public class SamePersonAffiliationException extends CommandException {

    /**
     * Constructs a SamePersonAffiliationException.
     * @param personNotAdded The person that fails to add in affiliation.
     */
    public SamePersonAffiliationException(Person personNotAdded) {
        super(personNotAdded.getName().toString() + " cannot be added as it is the same person.");
    }
}
