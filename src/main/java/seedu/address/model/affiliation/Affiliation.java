package seedu.address.model.affiliation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Affiliation in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAffiliationName(String)}
 */
public class Affiliation {

    public static final String MESSAGE_CONSTRAINTS = "Affiliations names should be alphanumeric and "
            + "spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String affiliationName;

    /**
     * Constructs a {@code Affiliation}.
     *
     * @param affiliationName A valid affiliation name.
     */
    public Affiliation(String affiliationName) {
        requireNonNull(affiliationName);
        checkArgument(isValidAffiliationName(affiliationName), MESSAGE_CONSTRAINTS);
        this.affiliationName = affiliationName;
    }

    /**
     * Returns true if a given string is a valid affiliation name.
     */
    public static boolean isValidAffiliationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Affiliation)) {
            return false;
        }

        Affiliation otherAffiliation = (Affiliation) other;
        return affiliationName.equals(otherAffiliation.affiliationName);
    }

    @Override
    public int hashCode() {
        return affiliationName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + affiliationName + ']';
    }

}
