package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRoomCommand object.
 */
public class DeleteRoomCommandParser implements Parser<DeleteRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteRoomCommand
     * and returns an DeleteRoomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteRoomCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROOMNUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROOMNUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteRoomCommand.MESSAGE_USAGE)
            );
        }
        String roomNum = argMultimap.getValue(PREFIX_ROOMNUMBER).get();
        return new DeleteRoomCommand(roomNum);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}