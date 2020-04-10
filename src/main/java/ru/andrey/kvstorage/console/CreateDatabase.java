package ru.andrey.kvstorage.console;

public class CreateDatabase implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;

    CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        this.environment.addDatabase(null);
        return DatabaseCommandResult.success("Database " + this.databaseName + " was created.");
    }
}