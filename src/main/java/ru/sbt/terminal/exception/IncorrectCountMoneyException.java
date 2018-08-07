package ru.sbt.terminal.exception;

public class IncorrectCountMoneyException extends Exception {
    private double incorrectCountMoney;
    private double countMoneyCard;
    public double getIncorrectCountMoney(){return incorrectCountMoney;}
    public double getCountMoneyCard(){return countMoneyCard;}

    public IncorrectCountMoneyException(String message){
        super(message);
    }

    public IncorrectCountMoneyException(String message, double incorrectCountMoney){
        super(message);
        this.incorrectCountMoney=incorrectCountMoney;
    }

    public IncorrectCountMoneyException(String message, double incorrectCountMoney, double countMoneyCard){
        super(message);
        this.incorrectCountMoney=incorrectCountMoney;
        this.countMoneyCard=countMoneyCard;
    }
}
