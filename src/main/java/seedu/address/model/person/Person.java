package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Role role;
    private final Set<Affiliation> affiliations = new HashSet<>();

    private final ToStringBuilder stringRepresentation;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Role role, Set<Affiliation> affiliations) {
        requireAllNonNull(name, phone, email, role, affiliations);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.affiliations.addAll(affiliations);
        this.stringRepresentation = new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("role", role)
                .add("affiliations", affiliations);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns an immutable affiliation set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Affiliation> getAffiliations() {
        return Collections.unmodifiableSet(affiliations);
    }

    public ToStringBuilder getStringRepresentation() {
        return stringRepresentation;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && role.equals(otherPerson.role)
                && affiliations.equals(otherPerson.affiliations);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, affiliations);
    }

    @Override
    public String toString() {
        return stringRepresentation.toString();
    }

}
