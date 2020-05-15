package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String tableName;
    private String databaseName;
    private String objectKey;

    public ReadKey(ExecutionEnvironment environment, String databaseName, String tableName, String objectKey) {
        this.environment = environment;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.objectKey = objectKey;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> database = environment.getDatabase(databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error(String.format("Database %s doesn't exist.", databaseName));
        }
        try {
            String result = database.get().read(tableName, objectKey);
            return DatabaseCommandResult.success(String.format("Value of key %s is %s", objectKey, result));
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}