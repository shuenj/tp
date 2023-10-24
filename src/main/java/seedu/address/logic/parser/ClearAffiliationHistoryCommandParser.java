package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearAffiliationHistoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new ClearAffiliationHistoryCommand object
 */
public class ClearAffiliationHistoryCommandParser implements Parser<ClearAffiliationHistoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearAffiliationHistoryCommand
     * and returns a ClearAffiliationHistoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearAffiliationHistoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearAffiliationHistoryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearAffiliationHistoryCommand.MESSAGE_USAGE), pe);
        }
    }
}
