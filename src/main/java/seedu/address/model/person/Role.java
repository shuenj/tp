package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    /**
     * Represents different types of roles in MediSync.
     */
    public enum Type {
        DOCTOR,
        PATIENT
    }

    /*
     * Role must be exactly one of the types. It is case-insensitive.
     */
    public static final String VALIDATION_REGEX = "(?i)" + new HashSet<>(Arrays.asList(Type.PATIENT, Type.DOCTOR))
            .stream().map(Enum::toString).collect(Collectors.joining("|"));

    public static final String MESSAGE_CONSTRAINTS = "Role can only be Doctor or Patient, and it should not be blank";

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
