package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AffiliationCommand;

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
                new AffiliationCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedAffiliationCommand);

    }

}
