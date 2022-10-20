package org.challenger.storageservice.exception;

import java.util.function.Supplier;

/**
 * @author u.dubok
 * @since 10/20/2022
 */

public class OrderNotFoundException extends RuntimeException {
    /**
     * A constructor with message.
     *
     * @param message to be logged
     */
    public OrderNotFoundException(final String message) {
        super(message);
    }

    /**
     * A simplified builder of exception.
     *
     * @param token of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<OrderNotFoundException> withToken(final Object token) {
        return () -> new OrderNotFoundException(String.format("Order with token '%s' not found", token));
    }

    /**
     * A simplified builder of exception.
     *
     * @param id of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<OrderNotFoundException> withId(final Object id) {
        return () -> new OrderNotFoundException(String.format("Order with id '%s' not found", id));
    }
}
