package seedu.address.model.affiliation.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

public class AffiliationPersonNotFoundException extends CommandException {

    /**
     * Constructs a AffiliationPersonNotFoundException.
     * @param nameOfPersonNotFound The name of person that is not found in address book.
     */
    public AffiliationPersonNotFoundException(String nameOfPersonNotFound) {
        super(nameOfPersonNotFound + " cannot be added as it does not exist in contact");
    }
}
