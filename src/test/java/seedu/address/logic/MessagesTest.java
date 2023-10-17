package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.BENSON;

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
        assertEquals("Benson Meier; Phone: 98765432; Email: johnd@example.com; Role: Patient; "
                + "Affiliations: {Alice Pauline, Carl Kurz}; Affiliation History: {Alice Pauline, Carl Kurz}",
                Messages.format(BENSON));
    }
}
