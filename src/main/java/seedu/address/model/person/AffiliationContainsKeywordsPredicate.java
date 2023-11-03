package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Affiliation} matches any of the keywords given.
 */
public class AffiliationContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public AffiliationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getAffiliations().stream().anyMatch(
                        affiliation -> affiliation.affiliationName.toLowerCase().contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AffiliationContainsKeywordsPredicate)) {
            return false;
        }

        AffiliationContainsKeywordsPredicate
                otherAffiliationContainsKeywordsPredicate = (AffiliationContainsKeywordsPredicate) other;
        return keywords.equals(otherAffiliationContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
