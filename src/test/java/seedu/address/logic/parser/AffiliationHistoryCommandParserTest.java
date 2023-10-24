package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AffiliationHistoryCommand;

public class AffiliationHistoryCommandParserTest {

    private AffiliationHistoryCommandParser parser = new AffiliationHistoryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AffiliationHistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAffiliationCommand() {
        // no leading and trailing whitespaces
        AffiliationHistoryCommand expectedAffiliationHistoryCommand =
                new AffiliationHistoryCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedAffiliationHistoryCommand);

    }

}
