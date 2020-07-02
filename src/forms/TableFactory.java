package forms;

import models.entities.TableType;

public class TableFactory {
    private TableFactory() {
    }

    public static final Table getTable(TableType bankType) {
        switch (bankType) {

            case depot:
                return new TableDepot();

            default:
                throw new IllegalArgumentException("This table type is unsupported");
        }
    }

}

