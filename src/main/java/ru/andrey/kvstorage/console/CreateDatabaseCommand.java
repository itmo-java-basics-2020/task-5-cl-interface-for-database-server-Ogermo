package ru.andrey.kvstorage.console;

public class CreateDatabaseCommand implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;

    CreateDatabaseCommand(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        environment.addDatabase(null);
        return DatabaseCommandResult.success(String.format("Database %s was created", databaseName));
    }
}