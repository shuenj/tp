package seedu.address.model.affiliation;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AffiliationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Affiliation(null));
    }

    @Test
    public void constructor_invalidAffiliationName_throwsIllegalArgumentException() {
        String invalidAffiliationName = "";
        assertThrows(IllegalArgumentException.class, () -> new Affiliation(invalidAffiliationName));
    }

    @Test
    public void isValidAffiliationName() {
        // null affiliation name
        assertThrows(NullPointerException.class, () -> Affiliation.isValidAffiliationName(null));
    }

}
