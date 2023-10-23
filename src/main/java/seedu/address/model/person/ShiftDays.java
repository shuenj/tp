package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Represents a set of ShiftDays in the contact list.
 */
public class ShiftDays implements Iterable<Integer> {

    public static final int SMALLEST_DAY_REPRESENTATION = 1;
    public static final int NUMBER_OF_DAYS_IN_WEEK = 7;

    public static final Map<Integer, String> DAY_OF_WEEK_MAP = new HashMap<>() {{
            put(1, "Mon");
            put(2, "Tue");
            put(3, "Wed");
            put(4, "Thu");
            put(5, "Fri");
            put(6, "Sat");
            put(7, "Sun");
        }};

    public static final String MESSAGE_CONSTRAINTS = "Indication of shift days should be integers "
            + "between 1 to 7 inclusive.";

    public final Set<Integer> shiftDays;

    /**
     * Constructs a default {@code ShiftDays} with no shifts.
     */
    public ShiftDays() {
        this.shiftDays = new HashSet<>();
    }

    /**
     * Constructs a {@code ShiftDays}.
     *
     * @param shiftDays A valid set of shift day integers.
     */
    public ShiftDays(Set<Integer> shiftDays) {
        requireNonNull(shiftDays);
        checkArgument(isValidShiftDays(shiftDays), MESSAGE_CONSTRAINTS);
        this.shiftDays = shiftDays;
    }

    /**
     * Returns true if a given set contains valid shift day integers.
     */
    public static boolean isValidShiftDays(Set<Integer> test) {
        return test
                .stream()
                .allMatch(shiftDay -> shiftDay >= SMALLEST_DAY_REPRESENTATION && shiftDay <= NUMBER_OF_DAYS_IN_WEEK);
    }

    /**
     * Returns the set of shiftDays.
     */
    public Set<Integer> getShiftDays() {
        return shiftDays;
    }

    /**
     * Modifies the set of shift days.
     *
     * @param shiftDays The set of integers representing the shift days.
     */
    public ShiftDays modifyShiftDays(Set<Integer> shiftDays) {
        this.shiftDays.clear();
        this.shiftDays.addAll(shiftDays);
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShiftDays)) {
            return false;
        }

        ShiftDays otherShiftDays = (ShiftDays) other;
        return shiftDays.equals(otherShiftDays.shiftDays);
    }

    @Override
    public int hashCode() {
        return shiftDays.hashCode();
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        ArrayList<Integer> sortedShiftDays = new ArrayList<>(shiftDays);
        Collections.sort(sortedShiftDays);

        ArrayList<String> daysOfWeek = new ArrayList<>();
        for (int shiftDay : sortedShiftDays) {
            daysOfWeek.add(DAY_OF_WEEK_MAP.get(shiftDay));
        }

        return "[" + String.join(", ", daysOfWeek) + "]";
    }

    /**
     * Enables an iterator to loop through shift days.
     */
    @Override
    public Iterator<Integer> iterator() {
        return shiftDays.iterator();
    }

}
