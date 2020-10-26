package br.com.emerson.math;

public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtract(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiply(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double divide(Double numberOne, Double numberTwo) {
        if (numberTwo == 0D) {
            throw  new ArithmeticException("Impossible to divide by zero!");
        }
        return numberOne / numberTwo;
    }

    public Double media(Double numberOne, Double numberTwo) {
        return sum(numberOne, numberTwo)/2;
    }

    public Double squareRoot(Double number) {
        return (Double) Math.sqrt(number);
    }
}
