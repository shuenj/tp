package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.affiliation.Affiliation;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameMatchesAffiliationPredicate implements Predicate<Person> {
    private final Set<Affiliation> affiliations;

    public NameMatchesAffiliationPredicate(Set<Affiliation> affiliations) {
        this.affiliations = affiliations;
    }

    @Override
    public boolean test(Person person) {
        return affiliations.contains(new Affiliation(person.getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameMatchesAffiliationPredicate)) {
            return false;
        }

        NameMatchesAffiliationPredicate otherNameMatchesAffiliationPredicate = (NameMatchesAffiliationPredicate) other;
        return affiliations.equals(otherNameMatchesAffiliationPredicate.affiliations);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("affiliations", affiliations).toString();
    }
}
