package ru.sbt.terminal;

public interface TerminalWindow {
    String verifyPin(String cardNumber, int pin);
     String verifyCountMoney(String cardNumber);
     String setMoney(String cardNumber, long money);
     String getMoney(String cardNumber, long money);
}
