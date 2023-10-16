package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;

public class MessagesTest {

    @Test
    public void display_duplicatePrefix_printCorrectMessage() {
        assertEquals(Messages.MESSAGE_DUPLICATE_FIELDS + "p",
                Messages.getErrorMessageForDuplicatePrefixes(new Prefix("p")));
    }

    @Test
    public void generate_userDisplay_printCorrectDisplay() {
        assertEquals("Alice Pauline; Phone: 94351253; Email: alice@example.com; "
                + "Role: Doctor; Affiliations: {Benson Meier}; "
                + "Affiliation History: {Benson Meier}", Messages.format(ALICE));
    }
}
