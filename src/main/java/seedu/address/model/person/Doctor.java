package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Doctor in the contact list.
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email,
        Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Doctor"), affiliations, affiliationHistory);
    }

    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Doctor(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Doctor"), affiliations);
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }

}
