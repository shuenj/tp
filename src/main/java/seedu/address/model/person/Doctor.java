package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Doctor in the contact list.
 */
public class Doctor extends Person {

    private final ShiftDays shiftDays;

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Phone phone, Email email,
        Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, new Role("Doctor"), affiliations, affiliationHistory);

        // Instantiation of Doctor should always begin with empty shift days.
        shiftDays = new ShiftDays();
    }

    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Doctor(Name name, Phone phone, Email email, Set<Affiliation> affiliations) {
        super(name, phone, email, new Role("Doctor"), affiliations);

        // Instantiation of Doctor should always begin with empty shift days.
        shiftDays = new ShiftDays();
    }

    public ShiftDays getShiftDays() {
        return shiftDays;
    }

    public Doctor setShiftDays(ShiftDays shiftDays) {
        this.shiftDays.modifyShiftDays(shiftDays.getShiftDays());
        return this;
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
                && getAffiliations().equals(otherDoctor.getAffiliations())
                && getAffiliationHistory().equals(otherDoctor.getAffiliationHistory())
                && shiftDays.equals(otherDoctor.getShiftDays());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getRole(), getAffiliations(),
                getAffiliationHistory(), shiftDays);
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation()
                .add("shiftDays", shiftDays)
                .toString();
    }

}
