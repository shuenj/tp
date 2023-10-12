package seedu.address.model.affiliation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.affiliation.exceptions.AffiliationPersonNotFoundException;
import seedu.address.model.affiliation.exceptions.SamePersonAffiliationException;
import seedu.address.model.affiliation.exceptions.SameRoleAffiliationException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AffiliationCheckerTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void check_withAffiliationPersonNotExist_affiliationPersonNotFoundException() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_AFFILIATION_BOB)
                .build();
        assertThrows(AffiliationPersonNotFoundException.class, () -> AffiliationChecker.check(person, model));
    }

    @Test
    public void check_withAffiliationPersonSameAsPersonAdding_samePersonAffiliationException() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_ALICE)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .build();
        assertThrows(SamePersonAffiliationException.class, () -> AffiliationChecker.check(person, model));
    }

    @Test
    public void check_withAffiliationPersonSameRoleAsPersonAdding_sameRoleAffiliationException() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_BOB)
                .withRole(VALID_ROLE_AMY)
                .withAffiliations(VALID_NAME_ALICE)
                .build();
        assertThrows(SameRoleAffiliationException.class, () -> AffiliationChecker.check(person, model));
    }

    @Test
    public void check_validAffiliation_returnsTrue() {
        Person person = new PersonBuilder()
                .withName(VALID_NAME_BOB)
                .withRole(VALID_ROLE_BOB)
                .withAffiliations(VALID_NAME_ALICE)
                .build();
        try {
            assertTrue(AffiliationChecker.check(person, model));
        } catch (CommandException ce) {
            fail();
        }
    }
}
