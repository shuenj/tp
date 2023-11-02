package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Patient in the contact list.
 */
public class Patient extends Person {

    private NextOfKin nextOfKin;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email,
        Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Patient"), affiliations, affiliationHistory);
        this.nextOfKin = new NextOfKin();
    }

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Patient"), affiliations);
        this.nextOfKin = new NextOfKin();
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
                && getNextOfKin().equals(otherPatient.getNextOfKin());
    }

    @Override
    public ToStringBuilder getStringBuilderRepresentation() {
        return super.getStringBuilderRepresentation().add("nextOfKin", getNextOfKin());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getRole(), getAffiliations(),
                getAffiliationHistory(), getNextOfKin());
    }

    public NextOfKin getNextOfKin() {
        return this.nextOfKin;
    }

    public Patient setNextOfKin(NextOfKin newNextOfKin) {
        this.nextOfKin = newNextOfKin;
        return this;
    }

    public Patient clearNextOfKins() {
        this.nextOfKin = new NextOfKin();
        return this;
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }
}
