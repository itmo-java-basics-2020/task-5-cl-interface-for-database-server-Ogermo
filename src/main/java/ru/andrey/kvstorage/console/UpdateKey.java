package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String tableName;
    private String databaseName;
    private String objectKey;
    private String objectValue;

    public UpdateKey(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey, String objectValue) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
        this.objectValue = objectValue;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database %s doesn't exist.", databaseName));
        }
        try {
            database.get().write(tableName, objectKey, objectValue);
            return DatabaseCommandResult.success(String.format("Key %s was updated with value %s", objectKey, objectValue));
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}