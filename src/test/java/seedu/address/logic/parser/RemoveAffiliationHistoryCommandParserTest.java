package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveAffiliationHistoryCommand;

public class RemoveAffiliationHistoryCommandParserTest {
    private RemoveAffiliationHistoryCommandParser parser = new RemoveAffiliationHistoryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveAffiliationHistoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        RemoveAffiliationHistoryCommand expectedRemoveAffiliationHistoryCommand =
                new RemoveAffiliationHistoryCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedRemoveAffiliationHistoryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n  1   \n", expectedRemoveAffiliationHistoryCommand);
    }
}
