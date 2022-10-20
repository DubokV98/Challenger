package org.challenger.storageservice.exception;

import java.util.function.Supplier;

/**
 * @author u.dubok
 * @since 10/16/2022
 */
public class MotorcycleNotFoundException extends RuntimeException {
    /**
     * A constructor with message.
     *
     * @param message to be logged
     */
    public MotorcycleNotFoundException(final String message) {
        super(message);
    }

    /**
     * A simplified builder of exception.
     *
     * @param token of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<MotorcycleNotFoundException> withToken(final Object token) {
        return () -> new MotorcycleNotFoundException(String.format("Motorcycle with token '%s' not found", token));
    }

    /**
     * A simplified builder of exception.
     *
     * @param id of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<MotorcycleNotFoundException> withId(final Object id) {
        return () -> new MotorcycleNotFoundException(String.format("Motorcycle with id '%s' not found", id));
    }
}
