package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Patient's Next of Kin in the contact list.
 */
public class NextOfKin {

    public static final String MESSAGE_NEXT_OF_KIN_NOT_EXIST = "This person does not have a Next-of-Kin";

    public final Name name;
    public final Phone phone;
    public final Relationship relationship;

    /**
     * Every field must be present and not null.
     */
    public NextOfKin(Name name, Phone phone, Relationship relationship) {
        requireAllNonNull(name, phone, relationship);
        this.name = name;
        this.phone = phone;
        this.relationship = relationship;
    }

    /**
     * Constructs a NextOfKin with no parameters.
     */
    public NextOfKin() {
        this.name = null;
        this.phone = null;
        this.relationship = null;
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Relationship getRelationship() {
        return this.relationship;
    }

    public boolean isPresent() {
        return this.name != null;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NextOfKin)) {
            return false;
        }

        NextOfKin otherNextOfKin = (NextOfKin) other;
        if (this.isPresent()) {
            return getName().equals(otherNextOfKin.getName())
                    && getPhone().equals(otherNextOfKin.getPhone())
                    && getRelationship().equals(otherNextOfKin.getRelationship());
        } else {
            return !otherNextOfKin.isPresent();
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getRelationship());
    }

    @Override
    public String toString() {
        if (this.isPresent()) {
            return "{name=" + name + ", phone=" + phone + ", relationship=" + relationship + "}";
        } else {
            return MESSAGE_NEXT_OF_KIN_NOT_EXIST;
        }
    }
}
