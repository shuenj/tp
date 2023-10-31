package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Patient in the contact list.
 */
public class Patient extends Person {

    private final Set<NextOfKin> nextOfKins;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email,
        Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Patient"), affiliations, affiliationHistory);
        this.nextOfKins = new HashSet<>();
    }

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Patient"), affiliations);
        this.nextOfKins = new HashSet<>();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return getName().equals(otherPatient.getName())
                && getPhone().equals(otherPatient.getPhone())
                && getEmail().equals(otherPatient.getEmail())
                && getRole().equals(otherPatient.getRole())
                && getAffiliations().equals(otherPatient.getAffiliations())
                && getAffiliationHistory().equals(otherPatient.getAffiliationHistory())
                && getNextOfKins().equals(otherPatient.getNextOfKins());
    }

    @Override
    public ToStringBuilder getStringBuilderRepresentation() {
        return super.getStringBuilderRepresentation().add("nextOfKins", getNextOfKins());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getRole(), getAffiliations(),
                getAffiliationHistory(), getNextOfKins());
    }

    public Set<NextOfKin> getNextOfKins() {
        return this.nextOfKins;
    }

    public void addNextOfKin(NextOfKin newNextOfKin) {
        this.nextOfKins.add(newNextOfKin);
    }

    public void setNextOfKins(Set<NextOfKin> newNextOfKins) {
        this.nextOfKins.clear();
        this.nextOfKins.addAll(newNextOfKins);
    }

    public void clearNextOfKins() {
        this.nextOfKins.clear();
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }
}
