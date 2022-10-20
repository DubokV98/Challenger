package org.challenger.storageservice.exception;

import java.util.function.Supplier;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
public class BrandNotFoundException extends RuntimeException {
    /**
     * A constructor with message.
     *
     * @param message to be logged
     */
    public BrandNotFoundException(final String message) {
        super(message);
    }

    /**
     * A simplified builder of exception.
     *
     * @param token of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<BrandNotFoundException> withToken(final Object token) {
        return () -> new BrandNotFoundException(String.format("Brand with token '%s' not found", token));
    }

    /**
     * A simplified builder of exception.
     *
     * @param id of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<BrandNotFoundException> withId(final Object id) {
        return () -> new BrandNotFoundException(String.format("Brand with id '%s' not found", id));
    }

}
