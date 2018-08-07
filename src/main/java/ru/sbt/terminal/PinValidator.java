package ru.sbt.terminal;

import ru.sbt.terminal.exception.AccountIsLockedException;
import ru.sbt.terminal.exception.PinValidatorException;

public interface PinValidator {
     boolean validator(String cardNumber, int pin)  throws PinValidatorException, AccountIsLockedException;
     boolean isVerifyPin(String cardNumber);
}
