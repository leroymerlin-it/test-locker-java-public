package it.leroymerlin.locker.infrastructure;

public class ExceptionBuilder {

    public static void raiseIllegalArgumentException(String paramName, Object invalidValue) {
        throw new IllegalArgumentException("Invalid value \"" + invalidValue + "\" for the param \"" + paramName + "\"");
    }

}
