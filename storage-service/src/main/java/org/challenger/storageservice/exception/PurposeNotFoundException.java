package org.challenger.storageservice.exception;

import java.util.function.Supplier;

/**
 * @author u.dubok
 * @since 10/7/2022
 */
public class PurposeNotFoundException extends RuntimeException {
    /**
     * A constructor with message.
     *
     * @param message to be logged
     */
    public PurposeNotFoundException(final String message) {
        super(message);
    }

    /**
     * A simplified builder of exception.
     *
     * @param token of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<PurposeNotFoundException> withToken(final Object token) {
        return () -> new PurposeNotFoundException(String.format("Purpose with token '%s' not found", token));
    }

    /**
     * A simplified builder of exception.
     *
     * @param id of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<PurposeNotFoundException> withId(final Object id) {
        return () -> new PurposeNotFoundException(String.format("Purpose with id '%s' not found", id));
    }
}
