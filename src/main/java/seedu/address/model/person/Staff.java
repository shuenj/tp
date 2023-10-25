package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Staff in the contact list.
 */
public class Staff extends Person {

    private final ShiftDays shiftDays;

    /**
     * Every field must be present and not null, except affiliationHistory.
     */
    public Staff(Name name, Phone phone, Email email, Role role,
            Set<Affiliation> affiliations, Set<Affiliation> affiliationHistory) {
        super(name, phone, email, role, affiliations, affiliationHistory);

        // Instantiation of Staff should always begin with empty shift days.
        shiftDays = new ShiftDays();
    }

    /**
     * Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, Role role, Set<Affiliation> affiliations) {
        super(name, phone, email, role, affiliations);

        // Instantiation of Staff should always begin with empty shift days.
        shiftDays = new ShiftDays();
    }

    public ShiftDays getShiftDays() {
        return shiftDays;
    }

    public Staff setShiftDays(ShiftDays shiftDays) {
        this.shiftDays.modifyShiftDays(shiftDays.getShiftDays());
        return this;
    }

    @Override
    public ToStringBuilder getStringBuilderRepresentation() {
        return super.getStringBuilderRepresentation().add("shiftDays", shiftDays);
    }

    /**
     * Returns true if both staff have the same identity and data fields.
     * This defines a stronger notion of equality between two staff members.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Staff)) {
            return false;
        }

        Staff otherStaff = (Staff) other;
        return getName().equals(otherStaff.getName())
                && getPhone().equals(otherStaff.getPhone())
                && getEmail().equals(otherStaff.getEmail())
                && getRole().equals(otherStaff.getRole())
                && getAffiliations().equals(otherStaff.getAffiliations())
                && getAffiliationHistory().equals(otherStaff.getAffiliationHistory())
                && shiftDays.equals(otherStaff.getShiftDays());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getRole(), getAffiliations(),
                getAffiliationHistory(), shiftDays);
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }

}
