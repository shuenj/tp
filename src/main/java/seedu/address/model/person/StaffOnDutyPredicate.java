package seedu.address.model.person;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person} is an instance of a {@code Staff} and that its {@code ShiftDays}
 * contains a particular day of the week.
 */
public class StaffOnDutyPredicate implements Predicate<Person> {

    public final int dayOfWeek;

    /**
     * Constructs a {@code StaffOnDutyPredicate} with the current day of the week indicated as target day.
     */
    public StaffOnDutyPredicate() {
        this.dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * Constructs a {@code StaffOnDutyPredicate} with an indicated day of the week.
     */
    public StaffOnDutyPredicate(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Staff)) {
            return false;
        }

        return ((Staff) person).isWorkingOn(dayOfWeek);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StaffOnDutyPredicate)) {
            return false;
        }

        StaffOnDutyPredicate otherStaffOnDutyPredicate = (StaffOnDutyPredicate) other;
        return dayOfWeek == otherStaffOnDutyPredicate.dayOfWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("dayOfWeek", dayOfWeek).toString();
    }
}
