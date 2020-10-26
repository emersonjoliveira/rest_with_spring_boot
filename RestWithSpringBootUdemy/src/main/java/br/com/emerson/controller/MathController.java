package br.com.emerson.controller;

import br.com.emerson.math.SimpleMath;
import br.com.emerson.request.validators.NumericValueValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static br.com.emerson.request.converters.NumberConverter.convertToDouble;

@RestController
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
        if (NumericValueValidator.isValidNumericValue(numberOne, numberTwo)) {
            return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
        }
        return 0D;
    }

    @RequestMapping(value="/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtract(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
        if (NumericValueValidator.isValidNumericValue(numberOne, numberTwo)) {
            return math.subtract(convertToDouble(numberOne), convertToDouble(numberTwo));
        }
        return 0D;
    }

    @RequestMapping(value="/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
        if (NumericValueValidator.isValidNumericValue(numberOne, numberTwo)) {
            return math.multiply(convertToDouble(numberOne), convertToDouble(numberTwo));
        }
        return 0D;
    }

    @RequestMapping(value="/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divide(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
        if (NumericValueValidator.isValidNumericValue(numberOne, numberTwo)) {
            return math.divide(convertToDouble(numberOne), convertToDouble(numberTwo));
        }
        return 0D;
    }

    @RequestMapping(value="/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) {
        if (NumericValueValidator.isValidNumericValue(numberOne, numberTwo)) {
            return math.media(convertToDouble(numberOne), convertToDouble(numberTwo));
        }
        return 0D;
    }

    @RequestMapping(value="/squareRoot/{number}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable(value = "number") String number) {
        if (NumericValueValidator.isValidNumericValue(number)) {
            return math.squareRoot(convertToDouble(number));
        }
        return 0D;
    }
}