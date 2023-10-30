package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SpecialisationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialisation(null));
    }

    @Test
    public void constructor_invalidSpecialisationName_throwsIllegalArgumentException() {
        String invalidSpecialisationName = "";
        assertThrows(IllegalArgumentException.class, () -> new Specialisation(invalidSpecialisationName));
    }

    @Test
    public void isValidSpecialisationName() {
        String validSpecialisationName = "Heart";
        assertTrue(Specialisation.isValidSpecialisationName(validSpecialisationName));
    }

    @Test
    public void isValidSpecialisationName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Specialisation.isValidSpecialisationName(null));
    }

    @Test
    public void equals() {
        Specialisation specialisation = new Specialisation("Heart");

        // same values -> returns true
        assertTrue(specialisation.equals(new Specialisation("Heart")));

        // same object -> returns true
        assertTrue(specialisation.equals(specialisation));

        // null -> returns false
        assertFalse(specialisation.equals(null));

        // different types -> returns false
        assertFalse(specialisation.equals(5.0f));

        // different values -> returns false
        assertFalse(specialisation.equals(new Specialisation("Lung")));
    }

    @Test
    public void toStringTest() {
        Specialisation specialisation = new Specialisation("Heart");
        assertEquals(specialisation.toString(), "Heart");
    }

    @Test
    public void hashCodeTest() {
        Specialisation specialisation = new Specialisation("Heart");
        assertEquals(specialisation.hashCode(), "Heart".hashCode());
    }
}
