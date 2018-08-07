package ru.sbt.terminal;

import ru.sbt.terminal.exception.*;

public interface Terminal {
     double verifyCountMoney(String cardNumber) throws PinValidatorException, ServerConnectException;
     void setMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException;
     void getMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException,
            IncorrectCountMoneyException;
     void verifyPin(String cardNumber, int pin) throws PinValidatorException, ServerConnectException,
            AccountIsLockedException;
}
