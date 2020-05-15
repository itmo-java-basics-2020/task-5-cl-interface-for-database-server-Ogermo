package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String result) {
        return new CommandResult(result, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String message) {
        return new CommandResult(message, DatabaseCommandStatus.FAILED);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class CommandResult implements DatabaseCommandResult {
        private String result;
        private String errorMessage;
        private DatabaseCommandStatus status;

        private CommandResult(String result, DatabaseCommandStatus status) {
            if (status == DatabaseCommandStatus.FAILED){
                this.errorMessage = result;
            } else {
                this.result = result;
            }
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return (status == DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}

