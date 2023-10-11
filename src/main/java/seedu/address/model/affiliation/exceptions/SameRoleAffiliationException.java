package seedu.address.model.affiliation.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;


/**
 * Throws a SameRoleAffiliationException exception which is a CommmandException
 * when the affiliation person's role is the same as the role of the person adding affiliation.
 */
public class SameRoleAffiliationException extends CommandException {

    /**
     * Constructs a SameRoleAffiliationException.
     * @param personNotAdded The person that fails to add in affiliation.
     */
    public SameRoleAffiliationException(Person personNotAdded) {
        super(personNotAdded.getName().toString() + " cannot be added as it has the same role "
                + personNotAdded.getRole() + ".");
    }
}
