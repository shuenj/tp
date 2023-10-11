package seedu.address.model.affiliation;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.affiliation.exceptions.AffiliationPersonNotFoundException;
import seedu.address.model.affiliation.exceptions.SamePersonAffiliationException;
import seedu.address.model.affiliation.exceptions.SameRoleAffiliationException;
import seedu.address.model.person.Person;

import java.util.Set;
import static java.util.Objects.requireNonNull;

/**
 * Check if the affiliation can be successfully added to a Person.
 */
public class AffiliationChecker {

    /**
     * The affiliation list to be checked
     */
    private final Set<Affiliation> affiliationList;

    private final Person personAddingAffiliation;

    /**
     * Constructs an Affiliation Checker.
     * @param personAddingAffiliation The person adding affiliation.
     */
    public AffiliationChecker(Person personAddingAffiliation) {
        requireNonNull(personAddingAffiliation);
        this.personAddingAffiliation = personAddingAffiliation;
        this.affiliationList = personAddingAffiliation.getAffiliations();
    }

    /**
     * Returns true if affiliation list pass the check, throws an exception otherwise.
     *
     * @param model The current model that is running.
     * @throws CommandException If affiliation list fails the check.
     */
    public Boolean check(Model model) throws CommandException {
        if (affiliationList.isEmpty()) {
            return true;
        }

        ReadOnlyAddressBook addressBook = model.getAddressBook();

        for (Affiliation affiliation: affiliationList) {
            Person affiliatedPerson = findAffiliatedPerson(affiliation, addressBook);
            if (affiliatedPerson == null) {
                throw new AffiliationPersonNotFoundException(affiliation.affiliationName);
            }

            if (affiliatedPerson.getName().equals(personAddingAffiliation.getName())) {
                throw new SamePersonAffiliationException(affiliatedPerson);
            }

            if (affiliatedPerson.getRole().equals(personAddingAffiliation.getRole())) {
                throw new SameRoleAffiliationException(affiliatedPerson);
            }
        }
        return true;
    }

    /**
     * Returns the person if affiliation person exist in address book, null otherwise.
     * @param affiliation The affiliation contains the affiliation person.
     * @param addressBook The address book to check for the affiliation person.
     */
    private Person findAffiliatedPerson(Affiliation affiliation, ReadOnlyAddressBook addressBook) {
        for (Person p: addressBook.getPersonList()) {
            if (p.getName().fullName.equals(affiliation.affiliationName)) {
                return p;
            }
        }
        return null;
    }
}
