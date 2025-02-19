package simplejavacalculator;

import static java.lang.Double.NaN;
import static java.lang.Math.*;

public class Calculator {

    public enum BiOperatorModes {
        ADD, MINUS, MULTIPLY, DIVIDE, X_POWER_OF_Y
    }

    public enum MonoOperatorModes {
        SQUARE, SQUARE_ROOT, ONE_DIVIDED_BY, COS, SIN, TAN, LOG, LN, RATE, ABS
    }

    private Double num1, num2;
    private BiOperatorModes mode = null;

    private Double calculateBiImpl() {
        switch (mode) {
            case ADD: return num1 + num2;
            case MINUS: return num1 - num2;
            case MULTIPLY: return num1 * num2;
            case DIVIDE: return num2 == 0 ? NaN : num1 / num2;
            case X_POWER_OF_Y: return pow(num1, num2);
            default: return NaN;
        }
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == null) {
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(null, num);
    }

    public Double reset() {
        num1 = null;
        num2 = null;
        mode = null;
        return NaN;
    }

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        switch (newMode) {
            case SQUARE: return num * num;
            case SQUARE_ROOT: return sqrt(num);
            case ONE_DIVIDED_BY: return num == 0 ? NaN : 1 / num;
            case COS: return cos(toRadians(num));
            case SIN: return sin(toRadians(num));
            case TAN: return tan(toRadians(num));
            case LOG: return log10(num);
            case LN: return log(num);
            case RATE: return num / 100;
            case ABS: return abs(num);
            default: throw new IllegalArgumentException("Invalid Mono Operator");
        }
    }
}
