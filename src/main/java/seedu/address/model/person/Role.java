package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.affiliation.Affiliation;

/**
 * Represents a Person's role in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    /**
     * Represents different types of role in MediSync.
     */
    public enum Type {
        DOCTOR,
        NURSE,
        PATIENT
    }

    /**
     * Role must be exactly one of the types. It is case-insensitive.
     */
    public static final String VALIDATION_REGEX = "(?i)" + Arrays.stream(Type.values()).map(Enum::name)
            .collect(Collectors.joining("|"));

    public static final String MESSAGE_CONSTRAINTS = "Role can only be Doctor, Nurse or Patient, "
            + "and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        value = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns person with its particular type based on the role.
     *
     * @param name Name of person.
     * @param phone Phone of person.
     * @param email Email of person.
     * @param affiliationList Affiliations of person.
     */
    public Person generatePerson(Name name, Phone phone, Email email,
        Set<Affiliation> affiliationList, Set<Affiliation> affiliationHistory) {

        assert value.toUpperCase().equals(Type.DOCTOR.name())
                || value.toUpperCase().equals(Type.NURSE.name())
                || value.toUpperCase().equals(Type.PATIENT.name());

        if (value.toUpperCase().equals(Type.DOCTOR.name())) {
            return new Doctor(name, phone, email, affiliationList, affiliationHistory);
        } else if (value.toUpperCase().equals(Type.NURSE.name())) {
            return new Nurse(name, phone, email, affiliationList, affiliationHistory);
        } else if (value.toUpperCase().equals(Type.PATIENT.name())) {
            return new Patient(name, phone, email, affiliationList, affiliationHistory);
        } else {
            return null;
        }
    }

    /**
     * Returns person with its particular type based on the role.
     *
     * @param name Name of person.
     * @param phone Phone of person.
     * @param email Email of person.
     * @param affiliationList Affiliations of person.
     */
    public Person generatePerson(Name name, Phone phone, Email email, Set<Affiliation> affiliationList) {

        assert value.toUpperCase().equals(Type.DOCTOR.name())
                || value.toUpperCase().equals(Type.NURSE.name())
                || value.toUpperCase().equals(Type.PATIENT.name());

        if (value.toUpperCase().equals(Type.DOCTOR.name())) {
            return new Doctor(name, phone, email, affiliationList);
        } else if (value.toUpperCase().equals(Type.NURSE.name())) {
            return new Nurse(name, phone, email, affiliationList);
        } else if (value.toUpperCase().equals(Type.PATIENT.name())) {
            return new Patient(name, phone, email, affiliationList);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return value.equals(otherRole.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
