package ru.sbt.terminal.exception;

public class AccountIsLockedException extends Exception {
    private long secToOnLock;

    public long getSecToOnLock(){
        return secToOnLock;
    }

    public AccountIsLockedException(String message, long secToOnLock) {
        super(message);
        this.secToOnLock=secToOnLock;
    }
}
