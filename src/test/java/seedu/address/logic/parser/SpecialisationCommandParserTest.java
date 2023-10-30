package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SpecialisationCommand;
import seedu.address.model.person.Specialisation;

public class SpecialisationCommandParserTest {

    private static final String VALID_SPECIALISATION = "Cardiology";
    private static final String VALID_SPECIALISATION_2 = "Oncology";
    private static final String VALID_SPECIALISATION_3 = "Neurology";
    private static final String VALID_SPECIALISATION_EMPTY = "";
    private static final String INVALID_SPECIALISATION = "Cardiology*";

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SpecialisationCommand.MESSAGE_USAGE);

    private SpecialisationCommandParser parser = new SpecialisationCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_SPECIALISATION, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptySpecialisation_success() {
        // no specialisation specified means clear specialisation
        assertParseSuccess(parser, "1 " + VALID_SPECIALISATION_EMPTY,
            new SpecialisationCommand(INDEX_FIRST_PERSON, new HashSet<>()));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_SPECIALISATION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_SPECIALISATION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidSpecialisation_failure() {
        assertParseFailure(parser, "1 " + INVALID_SPECIALISATION,
            MESSAGE_INVALID_FORMAT); // invalid specialisation

        // invalid specialisation followed by valid specialisation
        assertParseFailure(parser, "1 " + INVALID_SPECIALISATION + VALID_SPECIALISATION,
            MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + VALID_SPECIALISATION + ","
            + VALID_SPECIALISATION_2;

        HashSet<Specialisation> specialisations = new HashSet<>();
        specialisations.add(new Specialisation(VALID_SPECIALISATION));
        specialisations.add(new Specialisation(VALID_SPECIALISATION_2));

        SpecialisationCommand expectedCommand = new SpecialisationCommand(targetIndex,
            specialisations);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneSpecialisationSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + VALID_SPECIALISATION;

        HashSet<Specialisation> specialisations = new HashSet<>();
        specialisations.add(new Specialisation(VALID_SPECIALISATION));
        SpecialisationCommand expectedCommand = new SpecialisationCommand(targetIndex,
            specialisations);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleSpecialisationSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + VALID_SPECIALISATION + ","
            + VALID_SPECIALISATION_2 + ",";

        HashSet<Specialisation> specialisations = new HashSet<>();
        specialisations.add(new Specialisation(VALID_SPECIALISATION));
        specialisations.add(new Specialisation(VALID_SPECIALISATION_2));

        SpecialisationCommand expectedCommand = new SpecialisationCommand(targetIndex,
            specialisations);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedSpecialisationSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + VALID_SPECIALISATION + ", " + VALID_SPECIALISATION_2
            + ", " + VALID_SPECIALISATION + ", " + VALID_SPECIALISATION;

        HashSet<Specialisation> specialisations = new HashSet<>();
        specialisations.add(new Specialisation(VALID_SPECIALISATION));
        specialisations.add(new Specialisation(VALID_SPECIALISATION_2));
        specialisations.add(new Specialisation(VALID_SPECIALISATION));
        specialisations.add(new Specialisation(VALID_SPECIALISATION));

        SpecialisationCommand expectedCommand = new SpecialisationCommand(targetIndex,
            specialisations);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
