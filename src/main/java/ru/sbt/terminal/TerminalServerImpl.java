package ru.sbt.terminal;

import ru.sbt.terminal.exception.*;

public class TerminalServerImpl implements TerminalServer {
    private final PinValidator pinValidator;
    private int pinErrorCount = 0;
    private long timeLock=0;
    private long timeExpectation=5000;

    public TerminalServerImpl(PinValidator pinValidator) {
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean validatorPin(String cardNumber, int pin) throws PinValidatorException, ServerConnectException,
            AccountIsLockedException {
        if(isLock()) {
            throw new AccountIsLockedException("Превышена попытка ввода пин-кода. Терминал заблокирован", getTimeLock());
        }else{
            if(!pinValidator.validator(cardNumber, pin)){
                pinErrorCount++;
                if (pinErrorCount == 3) {
                    lock();
                    pinErrorCount=0;
                }
                throw new PinValidatorException("Пин-код не корректен, произведите ввод пин-кода заново");
            }
        }

        return true;
    }

    private boolean isLock(){
        if(timeLock!=0 && ((timeLock + timeExpectation - System.currentTimeMillis())>0)){
            return true;
        }
        timeLock=0;
        return false;
    }

    private void lock(){
        timeLock = System.currentTimeMillis();
    }

    private long getTimeLock(){
        return (timeLock + timeExpectation - System.currentTimeMillis())/1000;
    }

    @Override
    public double verifyCountMoney(String cardNumber) throws PinValidatorException, ServerConnectException {
        if(pinValidator.isVerifyPin(cardNumber)){
            throw new PinValidatorException("Пин-код не введен");
        }
        return 0;
    }

    @Override
    public void setMoney(String cardNumber, double money) throws PinValidatorException, ServerConnectException {
        if(pinValidator.isVerifyPin(cardNumber)){
            throw new PinValidatorException("Пин-код не введен");
        }
    }

    @Override
    public void getMoney(String cardNumber, long money) throws PinValidatorException, ServerConnectException,
            IncorrectCountMoneyException {
        if(pinValidator.isVerifyPin(cardNumber)){
            throw new PinValidatorException("Пин-код не введен");
        }
    }

    @Override
    public boolean isValidatorPin(String cardNumber) {
        return pinValidator.isVerifyPin(cardNumber);
    }
}
