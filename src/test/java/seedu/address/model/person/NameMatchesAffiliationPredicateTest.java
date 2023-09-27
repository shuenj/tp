package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.testutil.PersonBuilder;

public class NameMatchesAffiliationPredicateTest {

    private final Affiliation VALID_AFFILIATION_JOHN = new Affiliation("John Doe");

    @Test
    public void equals() {
        Set<Affiliation> firstAffiliationSet = Set.of(VALID_AFFILIATION_JOHN);
        Set<Affiliation> secondAffiliationSet = Set.of(VALID_AFFILIATION_JOHN,
                new Affiliation("Jill Wagon"));

        NameMatchesAffiliationPredicate firstPredicate = new NameMatchesAffiliationPredicate(firstAffiliationSet);
        NameMatchesAffiliationPredicate secondPredicate = new NameMatchesAffiliationPredicate(secondAffiliationSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameMatchesAffiliationPredicate firstPredicateCopy = new NameMatchesAffiliationPredicate(firstAffiliationSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_affiliationsSetContainsName_returnsTrue() {
        NameMatchesAffiliationPredicate predicate = new NameMatchesAffiliationPredicate(
                Collections.singleton(new Affiliation("John Doe")));
        assertTrue(predicate.test(new PersonBuilder().withName("John Doe").build()));

    }

    @Test
    public void test_affiliationsSetDoesNotContainName_returnsFalse() {
        NameMatchesAffiliationPredicate predicate = new NameMatchesAffiliationPredicate(
                Collections.singleton(new Affiliation("John Doe")));
        assertFalse(predicate.test(new PersonBuilder().withName("Jill Wagon").build()));
    }

    @Test
    public void toStringMethod() {
        Set<Affiliation> affiliations = Collections.singleton(new Affiliation("John Doe"));
        NameMatchesAffiliationPredicate predicate = new NameMatchesAffiliationPredicate(affiliations);

        String expected = NameMatchesAffiliationPredicate.class.getCanonicalName()
                + "{affiliations=" + affiliations + "}";
        assertEquals(expected, predicate.toString());
    }
}
