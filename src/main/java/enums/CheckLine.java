package enums;

public enum CheckLine {
    VERTICAL (new CheckDirection[] {CheckDirection.NORTH, CheckDirection.SOUTH}),
    LEFT_DIAGONAL (new CheckDirection[] {CheckDirection.NORTH_WEST, CheckDirection.SOUTH_EAST}),
    HORIZONTAL (new CheckDirection[] {CheckDirection.EAST, CheckDirection.WEST}),
    RIGHT_DIAGONAL (new CheckDirection[] {CheckDirection.NORTH_EAST, CheckDirection.SOUTH_WEST});

    public final CheckDirection[] directions;

    CheckLine(CheckDirection[] checkDirections) {
        directions = checkDirections;
    }
}
