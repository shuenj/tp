package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AffiliationHistoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.logging.Logger;

/**
 * Parses input arguments and creates a new AffiliationCommand object
 */
public class AffiliationHistoryCommandParser implements Parser<AffiliationHistoryCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AffiliationCommand
     * and returns a AffiliationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AffiliationHistoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AffiliationHistoryCommand(index);
        } catch (ParseException pe) {
            logger.finer("This user input caused a ParseException: " + args);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AffiliationHistoryCommand.MESSAGE_USAGE), pe);
        }
    }

}
