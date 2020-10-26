package br.com.emerson.request.converters;

import java.util.Objects;

public class NumberConverter {

    public static Double convertToDouble(String value) {
        if (isNumeric(value)) {
            return Double.parseDouble(value.replace(",", "."));
        }
        return 0D;
    }

    public static boolean isNumeric(String value) {
        if (Objects.isNull(value)) {
            return false;
        }
        return value.replace(",", ".").matches("[+-]?[0-9]*\\.?[0-9]+");
    }
}
