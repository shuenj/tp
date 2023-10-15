package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Patient in the contact list.
 */
public class Patient extends Person {

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email,
        Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Patient"), affiliations, affiliationHistory);
    }

    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Patient(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Patient"), affiliations);
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }
}
