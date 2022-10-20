package org.challenger.storageservice.exception;

import java.util.function.Supplier;

/**
 * @author u.dubok
 * @since 10/13/2022
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * A constructor with message.
     *
     * @param message to be logged
     */
    public UserNotFoundException(final String message) {
        super(message);
    }

    /**
     * A simplified builder of exception.
     *
     * @param username of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<UserNotFoundException> withUsername(final Object username) {
        return () -> new UserNotFoundException(String.format("User with username '%s' not found", username));
    }

    /**
     * A simplified builder of exception.
     *
     * @param id of the object
     * @return exception object wrapped in {@link Supplier}
     */
    public static Supplier<UserNotFoundException> withId(final Object id) {
        return () -> new UserNotFoundException(String.format("User with id '%s' not found", id));
    }
}
