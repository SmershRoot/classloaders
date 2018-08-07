package ru.sbt.terminal;

import ru.sbt.terminal.exception.*;

public class TerminalWindowImpl implements TerminalWindow {
    private final Terminal terminal;

    public TerminalWindowImpl(Terminal terminal){
        this.terminal=terminal;
    }

    @Override
    public String verifyPin(String cardNumber, int pin) {
        try {
            terminal.verifyPin(cardNumber,pin);
        } catch (AccountIsLockedException e) {
            e.printStackTrace();
            return "Ваш аккаунт заблокирован. Подождите "+e.getSecToOnLock()+"секунд до разблокировки.";
        } catch (PinValidatorException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ServerConnectException e) {
            e.printStackTrace();
            return "Соединение с сервером отсутствует. Попытайтесь воспользоваться терминалом позже.";
        } catch (Exception e){
            e.printStackTrace();
            return "Что-то пошло не так. Попытайтесь воспользоваться терминалом позже. В случае повторения ошибки " +
                    "обратитесь в службу технической поддержки";
        }

        return null;
    }

    @Override
    public String verifyCountMoney(String cardNumber) {
        String result;
        try {
            result = "На вашем счету " + terminal.verifyCountMoney(cardNumber);
        } catch (PinValidatorException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ServerConnectException e) {
            e.printStackTrace();
            return "Соединение с сервером отсутствует. Попытайтесь воспользоваться терминалом позже.";
        } catch (Exception e){
            e.printStackTrace();
            return "Что-то пошло не так. Попытайтесь воспользоваться терминалом позже. В случае повторения ошибки " +
                    "обратитесь в службу технической поддержки";
        }

        return result;
    }

    @Override
    public String setMoney(String cardNumber, long money) {
        String result;

        try {
            terminal.setMoney(cardNumber,money);
        } catch (PinValidatorException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ServerConnectException e) {
            e.printStackTrace();
            return "Соединение с сервером отсутствует. Попытайтесь воспользоваться терминалом позже.";
        } catch (Exception e){
            e.printStackTrace();
            return "Что-то пошло не так. Попытайтесь воспользоваться терминалом позже. В случае повторения ошибки " +
                    "обратитесь в службу технической поддержки";
        }

        result=money+" успешно зачислено на Вашу карту";

        return result;
    }

    @Override
    public String getMoney(String cardNumber, long money) {
        String result;

        try {
            terminal.getMoney(cardNumber,money);
        } catch (PinValidatorException | IncorrectCountMoneyException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ServerConnectException e) {
            e.printStackTrace();
            return "Соединение с сервером отсутствует. Попытайтесь воспользоваться терминалом позже";
        } catch (Exception e){
            e.printStackTrace();
            return "Что-то пошло не так. Попытайтесь воспользоваться терминалом позже. В случае повторения ошибки " +
                    "обратитесь в службу технической поддержки";
        }

        result=money + " успешно списано с Вашей карты";

        return result;
    }
}
