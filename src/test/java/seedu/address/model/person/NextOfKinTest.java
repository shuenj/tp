package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new NextOfKin(null, new Phone(VALID_PHONE_AMY), VALID_RELATIONSHIP_AMY));

        assertThrows(NullPointerException.class, () ->
                new NextOfKin(new Name(VALID_NAME_AMY), null, VALID_RELATIONSHIP_AMY));

        assertThrows(NullPointerException.class, () ->
                new NextOfKin(new Name(VALID_NAME_AMY), new Phone(VALID_PHONE_AMY), null));
    }

    @Test
    public void equals() {
        NextOfKin nextOfKin = VALID_NEXT_OF_KIN_AMY;

        // same values -> returns true
        assertTrue(nextOfKin.equals(new NextOfKin(new Name("Tom"),
                new Phone("11111111"), new Relationship("Parents"))));

        // same object -> returns true
        assertTrue(nextOfKin.equals(nextOfKin));

        // null -> returns false
        assertFalse(nextOfKin.equals(null));

        // different types -> returns false
        assertFalse(nextOfKin.equals(5.0f));

        // different values -> returns false
        assertFalse(nextOfKin.equals(new NextOfKin(new Name("Tom"),
                new Phone("11111111"), new Relationship("Spouse"))));

        //different class instance, same value -> return true
        NextOfKin nextOfKin1 = new NextOfKin(new Name("Tom"),
                new Phone("11111111"), new Relationship("Parents"));
        assertTrue(nextOfKin.hashCode() == nextOfKin1.hashCode());
    }

    @Test
    public void toStringMethod() {
        NextOfKin nextOfKin = VALID_NEXT_OF_KIN_AMY;
        String expected = NextOfKin.class.getCanonicalName() + "{"
                + "name=Tom, phone=11111111, relationship=Parents" + "}";
        assertEquals(expected, nextOfKin.toString());
    }
}
