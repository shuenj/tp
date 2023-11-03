package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AffiliationContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RoleContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneTag_throwsParseException() {
        assertParseFailure(parser, " n/Alice Bob p/88976879",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        // only one invalid tag
        assertParseFailure(parser, " z/Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // invalid and valid tag
        assertParseFailure(parser, " z/hello n/Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validArgsName_returnsFindCommand() {
        // no leading and trailing whitespaces, except first one
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsPhone_returnsFindCommand() {
        // no leading and trailing whitespaces, except first one
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("89786789", "98765432")));
        assertParseSuccess(parser, " p/89786789 98765432", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " p/ \n 89786789 \n \t 98765432  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsEmail_returnsFindCommand() {
        // no leading and trailing whitespaces, except first one
        FindCommand expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("a@gmail.com", "b@gmail.com")));
        assertParseSuccess(parser, " e/a@gmail.com b@gmail.com", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " e/ \n a@gmail.com \n \t b@gmail.com  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsRole_returnsFindCommand() {
        // no leading and trailing whitespaces, except first one
        FindCommand expectedFindCommand =
                new FindCommand(new RoleContainsKeywordsPredicate(Arrays.asList("Doctor", "Patient")));
        assertParseSuccess(parser, " r/Doctor Patient", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " r/ \n Doctor \n \t Patient  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsAffiliation_returnsFindCommand() {
        // no leading and trailing whitespaces, except first one
        FindCommand expectedFindCommand =
                new FindCommand(new AffiliationContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " a/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " a/ \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
