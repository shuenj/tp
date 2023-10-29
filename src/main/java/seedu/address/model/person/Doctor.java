package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Doctor in the contact list.
 */
public class Doctor extends Staff {

    private final Set<Specialisation> specialisations;
    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Doctor(Name name, Phone phone, Email email,
            Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Doctor"), affiliations, affiliationHistory);
        specialisations = new HashSet<>();
    }

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Doctor"), affiliations);
        specialisations = new HashSet<>();
    }

    public Set<Specialisation> getSpecialisations() {
        return specialisations;
    }

    public Set<Specialisation> setSpecialisations(Set<Specialisation> specialisations) {
        this.specialisations.clear();
        this.specialisations.addAll(specialisations);
        return this.specialisations;
    }

    /**
     * Returns true if both doctors have the same identity and data fields.
     * This defines a stronger notion of equality between two doctors.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor otherDoctor = (Doctor) other;
        return getName().equals(otherDoctor.getName())
                && getPhone().equals(otherDoctor.getPhone())
                && getEmail().equals(otherDoctor.getEmail())
                && getRole().equals(otherDoctor.getRole())
                && getAffiliations().equals(otherDoctor.getAffiliations())
                && getAffiliationHistory().equals(otherDoctor.getAffiliationHistory())
                && getShiftDays().equals(otherDoctor.getShiftDays())
                && getSpecialisations().equals(otherDoctor.getSpecialisations());
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
