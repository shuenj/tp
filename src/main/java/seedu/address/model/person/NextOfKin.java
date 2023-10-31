package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Patient's Next of Kin in the contact list.
 */
public class NextOfKin {

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

    public ToStringBuilder getStringBuilderRepresentation() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("relationship", relationship);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getRelationship());
    }

    @Override
    public String toString() {
        return getStringBuilderRepresentation().toString();
    }
}