package br.com.emerson.request.validators;

import br.com.emerson.exception.UnsupportedMathOperationException;
import br.com.emerson.request.converters.NumberConverter;

public class NumericValueValidator {

    public static boolean isValidNumericValue(String... values) {
        for (String value : values) {
            if (!NumberConverter.isNumeric(value)) {
                throw new UnsupportedMathOperationException("Please set a numeric value!");
            }
        }
        return true;
    }
}
