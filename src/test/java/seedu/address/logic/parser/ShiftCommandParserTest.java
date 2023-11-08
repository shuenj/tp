package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShiftCommand;
import seedu.address.model.person.ShiftDays;

public class ShiftCommandParserTest {

    private ShiftCommandParser parser = new ShiftCommandParser();

    @Test
    public void parse_validArgs_returnsShiftCommand() {
        Set<Integer> shiftDays = new HashSet<>(Arrays.asList(1, 3, 7));
        assertParseSuccess(parser, "1 137", new ShiftCommand(INDEX_FIRST_PERSON, shiftDays));
    }

    @Test
    public void parse_validArgsWithDuplicates_returnsShiftCommand() {
        // Although duplicates are provided, the set will remove them
        Set<Integer> shiftDays = new HashSet<>(Arrays.asList(1, 3, 7));
        assertParseSuccess(parser, "1 13737", new ShiftCommand(INDEX_FIRST_PERSON, shiftDays));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a 137", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidShiftDays_throwsParseException() {
        assertParseFailure(parser, "1 89", ShiftDays.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingShiftDays_clearsShiftDays() {
        Set<Integer> shiftDays = new HashSet<>();
        assertParseSuccess(parser, "1 ", new ShiftCommand(INDEX_FIRST_PERSON, shiftDays));
    }

    @Test
    public void parse_allValidDaysSpecified_success() {
        Set<Integer> shiftDays = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertParseSuccess(parser, "1 1234567", new ShiftCommand(INDEX_FIRST_PERSON, shiftDays));
    }

    @Test
    public void parse_noShiftDaysSpecified_success() {
        Set<Integer> shiftDays = new HashSet<>();
        assertParseSuccess(parser, "1", new ShiftCommand(INDEX_FIRST_PERSON, shiftDays));
    }
}
