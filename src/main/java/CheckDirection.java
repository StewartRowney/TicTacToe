public enum CheckDirection {
    NORTH {public Location getNextLocation(Location location) {return new Location(location.row() - 1, location.column());}},
    NORTH_EAST {public Location getNextLocation(Location location) {return new Location(location.row() - 1, location.column() + 1);}},
    EAST {public Location getNextLocation(Location location) {return new Location(location.row(), location.column() + 1);}},
    SOUTH_EAST {public Location getNextLocation(Location location) {return new Location(location.row() + 1, location.column() + 1);}},
    SOUTH {public Location getNextLocation(Location location) {return new Location(location.row() + 1, location.column());}},
    SOUTH_WEST {public Location getNextLocation(Location location) {return new Location(location.row() + 1, location.column() - 1);}},
    WEST {public Location getNextLocation(Location location) {return new Location(location.row(), location.column() - 1);}},
    NORTH_WEST {public Location getNextLocation(Location location) {return new Location(location.row() - 1, location.column() - 1);}};

    public abstract Location getNextLocation(Location location);
    }
