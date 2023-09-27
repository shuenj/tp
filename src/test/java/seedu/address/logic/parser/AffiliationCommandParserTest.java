package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AffiliationCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class AffiliationCommandParserTest {

    private AffiliationCommandParser parser = new AffiliationCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AffiliationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAffiliationCommand() {
        // no leading and trailing whitespaces
        AffiliationCommand expectedAffiliationCommand =
                new AffiliationCommand(new Name("Alice Sim"));
        assertParseSuccess(parser, "Alice Sim", expectedAffiliationCommand);

    }

}
