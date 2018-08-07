package ru.sbt.terminal;

import ru.sbt.terminal.exception.AccountIsLockedException;
import ru.sbt.terminal.exception.IncorrectCountMoneyException;
import ru.sbt.terminal.exception.PinValidatorException;
import ru.sbt.terminal.exception.ServerConnectException;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
    }

    @Override
    public double verifyCountMoney(String cardNumber) throws PinValidatorException, ServerConnectException {
        if(server.isValidatorPin(cardNumber)){
            return server.verifyCountMoney(cardNumber);
        }else{
            throw new PinValidatorException("Пин-код не введен, произведите ввод пин-кода");
        }
    }

    @Override
    public void setMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException {
        if(server.isValidatorPin(cardNumber)){
            server.setMoney(cardNumber, money);
        }else{
            throw new PinValidatorException("Пин-код не введен, произведите ввод пин-кода");
        }
    }

    @Override
    public void getMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException,
            IncorrectCountMoneyException {
        if(!server.isValidatorPin(cardNumber)){
            throw new PinValidatorException("Пин-код не введен, произведите ввод пин-кода");
        }
        if(money%100!=0){
            throw new IncorrectCountMoneyException("сумма должна быть картна 100");
        }
        server.getMoney(cardNumber,money);
    }

    @Override
    public void verifyPin(String cardNumber, int pin) throws PinValidatorException, ServerConnectException,
            AccountIsLockedException {
        server.validatorPin(cardNumber,pin);
    }
}
