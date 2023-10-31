package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShiftCommand object
 */
public class ShiftCommandParser implements Parser<ShiftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShiftCommand
     * and returns a ShiftCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShiftCommand parse(String args) throws ParseException {

        requireNonNull(args);
        try {
            String[] splitArgs = args.trim().split(" ");
            Index index = ParserUtil.parseIndex(splitArgs[0]);
            Set<Integer> shiftDayNumbers;
            // check if there are any arguments after the index
            if (splitArgs.length > 1 && !splitArgs[1].isEmpty()) {
                shiftDayNumbers = ParserUtil.parseShiftDays(splitArgs[1]);
            } else {
                shiftDayNumbers = ParserUtil.parseShiftDays("");
            }
            return new ShiftCommand(index, shiftDayNumbers);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShiftCommand.MESSAGE_USAGE), pe);
        }
    }

}
