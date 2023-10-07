package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_AFFILIATION = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ROLE = "Doctor";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_AFFILIATION_1 = "friend";
    private static final String VALID_AFFILIATION_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseAffiliation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAffiliation(null));
    }

    @Test
    public void parseAffiliation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAffiliation(INVALID_AFFILIATION));
    }

    @Test
    public void parseAffiliation_validValueWithoutWhitespace_returnsAffiliation() throws Exception {
        Affiliation expectedAffiliation = new Affiliation(VALID_AFFILIATION_1);
        assertEquals(expectedAffiliation, ParserUtil.parseAffiliation(VALID_AFFILIATION_1));
    }

    @Test
    public void parseAffiliation_validValueWithWhitespace_returnsTrimmedAffiliation() throws Exception {
        String affiliationWithWhitespace = WHITESPACE + VALID_AFFILIATION_1 + WHITESPACE;
        Affiliation expectedAffiliation = new Affiliation(VALID_AFFILIATION_1);
        assertEquals(expectedAffiliation, ParserUtil.parseAffiliation(affiliationWithWhitespace));
    }

    @Test
    public void parseAffiliations_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAffiliations(null));
    }

    @Test
    public void parseAffiliations_collectionWithInvalidAffiliations_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAffiliations(
                Arrays.asList(VALID_AFFILIATION_1, INVALID_AFFILIATION)));
    }

    @Test
    public void parseAffiliations_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAffiliations(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAffiliations_collectionWithValidAffiliations_returnsAffiliationSet() throws Exception {
        Set<Affiliation> actualAffiliationSet = ParserUtil
                .parseAffiliations(Arrays.asList(VALID_AFFILIATION_1, VALID_AFFILIATION_2));
        Set<Affiliation> expectedAffiliationSet = new HashSet<Affiliation>(
                Arrays.asList(new Affiliation(VALID_AFFILIATION_1), new Affiliation(VALID_AFFILIATION_2)));

        assertEquals(expectedAffiliationSet, actualAffiliationSet);
    }
}
