package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ShiftDaysTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ShiftDays(null));
    }

    @Test
    public void constructor_invalidShiftDays_throwsIllegalArgumentException() {
        Set<Integer> invalidShiftDays = new HashSet<>();
        invalidShiftDays.add(9);
        assertThrows(IllegalArgumentException.class, () -> new ShiftDays(invalidShiftDays));
    }

    @Test
    public void isValidShiftDays() {

        // null shift days set
        assertThrows(NullPointerException.class, () -> ShiftDays.isValidShiftDays(null));

        Set<Integer> invalidShiftDays = new HashSet<>();
        // invalid shift days
        invalidShiftDays.add(12);
        assertFalse(ShiftDays.isValidShiftDays(invalidShiftDays)); // number larger than 7
        invalidShiftDays.remove(12);
        invalidShiftDays.add(0);
        assertFalse(ShiftDays.isValidShiftDays(invalidShiftDays)); // number smaller than 1
        invalidShiftDays.add(15);
        assertFalse(ShiftDays.isValidShiftDays(invalidShiftDays)); // both number smaller than 1 and larger than 7

        Set<Integer> validShiftDays = new HashSet<>();
        // valid shift days
        assertTrue(ShiftDays.isValidShiftDays(validShiftDays)); // Empty Shift Days
        validShiftDays.add(6);
        assertTrue(ShiftDays.isValidShiftDays(validShiftDays)); // Saturday(6)
        validShiftDays.add(2);
        assertTrue(ShiftDays.isValidShiftDays(validShiftDays)); // Tuesday(2) and Saturday(6)
    }

    @Test
    public void equals() {
        ShiftDays shiftDays = new ShiftDays(new HashSet<>());

        // same values -> returns true
        assertTrue(shiftDays.equals(new ShiftDays(new HashSet<>())));

        // same object -> returns true
        assertTrue(shiftDays.equals(shiftDays));

        // null -> returns false
        assertFalse(shiftDays.equals(null));

        // different types -> returns false
        assertFalse(shiftDays.equals(5.0f));

        // different values -> returns false
        Set<Integer> otherShiftDaysSet = new HashSet<>();
        otherShiftDaysSet.add(5);
        assertFalse(shiftDays.equals(new ShiftDays(otherShiftDaysSet)));
    }
}
