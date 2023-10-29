package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalStaff.CARL;
import static seedu.address.testutil.TypicalStaff.ELLE;

import org.junit.jupiter.api.Test;

public class StaffOnDutyPredicateTest {

    @Test
    public void equals() {

        StaffOnDutyPredicate defaultPredicate = new StaffOnDutyPredicate();
        StaffOnDutyPredicate firstPredicate = new StaffOnDutyPredicate(1);
        StaffOnDutyPredicate secondPredicate = new StaffOnDutyPredicate(2);

        // same object -> returns true
        assertTrue(defaultPredicate.equals(defaultPredicate));

        // same values -> returns true
        StaffOnDutyPredicate firstPredicateCopy = new StaffOnDutyPredicate(1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different day of the week -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_staffShiftDaysContainsIndicatedDay_returnsTrue() {
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(3);
        assertTrue(predicate.test(CARL));
    }

    @Test
    public void test_staffShiftDaysDoesNotContainIndicatedDay_returnsFalse() {
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(2);
        assertFalse(predicate.test(ELLE));
    }

    @Test
    public void test_personNotStaff_returnsFalse() {
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(2);
        assertFalse(predicate.test(FIONA));
    }

    @Test
    public void toStringMethod() {
        StaffOnDutyPredicate predicate = new StaffOnDutyPredicate(7);

        String expected = StaffOnDutyPredicate.class.getCanonicalName() + "{dayOfWeek=" + 7 + "}";
        assertEquals(expected, predicate.toString());
    }
}
