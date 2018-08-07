package ru.sbt.terminal;

import ru.sbt.terminal.exception.*;

public interface TerminalServer {
     boolean validatorPin(String cardNumber, int pin)  throws PinValidatorException, ServerConnectException,
            AccountIsLockedException;
     double verifyCountMoney(String cardNumber) throws PinValidatorException, ServerConnectException;
     void setMoney(String cardNumber, double money) throws PinValidatorException, ServerConnectException;
     void getMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException,
            IncorrectCountMoneyException;
     boolean isValidatorPin(String cardNumber);

}
