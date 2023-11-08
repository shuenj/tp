package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SpecialisationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Specialisation;

/**
 * Parses input arguments and creates a new SpecialisationCommand object
 */
public class SpecialisationCommandParser implements Parser<SpecialisationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SpecialisationCommand
     * and returns a SpecialisationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SpecialisationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        // Limit set to -1 to include empty strings in the array
        String[] splitArgs = args.trim().split("\\s+", -1);
        Index index;
        try {
            index = ParserUtil.parseIndex(splitArgs[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SpecialisationCommand.MESSAGE_USAGE), pe);
        }
        Set<Specialisation> specialisations = new HashSet<>();

        // Check if there are any arguments after the index.
        if (splitArgs.length > 1 && !splitArgs[1].isEmpty()) {
            String specialisationString = String.join(" ", Arrays.copyOfRange(splitArgs, 1, splitArgs.length));
            specialisations = ParserUtil.parseSpecialisations(specialisationString);
        }
        return new SpecialisationCommand(index, specialisations);
    }
}
