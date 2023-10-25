package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Nurse in the contact list.
 */
public class Nurse extends Staff {

    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Nurse(Name name, Phone phone, Email email,
                  Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Nurse"), affiliations, affiliationHistory);
    }

    /**
     * Every field must be present and not null.
     */
    public Nurse(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Nurse"), affiliations);
    }

    /**
     * Returns true if both nurses have the same identity and data fields.
     * This defines a stronger notion of equality between two nurses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nurse)) {
            return false;
        }

        Nurse otherNurse = (Nurse) other;
        return getName().equals(otherNurse.getName())
                && getPhone().equals(otherNurse.getPhone())
                && getEmail().equals(otherNurse.getEmail())
                && getRole().equals(otherNurse.getRole())
                && getAffiliations().equals(otherNurse.getAffiliations())
                && getAffiliationHistory().equals(otherNurse.getAffiliationHistory())
                && getShiftDays().equals(otherNurse.getShiftDays());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getRole(), getAffiliations(),
                getAffiliationHistory(), getShiftDays());
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }
}
