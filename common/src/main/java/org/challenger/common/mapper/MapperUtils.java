package org.challenger.common.mapper;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author u.dubok
 * @since 10/04/2022
 */
@Slf4j
final class MapperUtils {

    /**
     * Creates an instance of provided type.
     *
     * @param <T> generic type for creation
     * @param cls class objects
     * @return new Instance created from class
     */
    static <T> T instantiate(final Class<T> cls) {
        try {
            return cls.getConstructor().newInstance();
        } catch (final Exception exception) {
            throw new IllegalStateException("Unable to instantiate " + cls.getSimpleName(), exception);
        }
    }

    /**
     * The helper method, that performs safe write and prints an exception in case if any thrown.
     *
     * @param source source field information
     * @param dest   destination field information
     * @param from   data source object
     * @param to     object to be updated
     */
    private static void safeWrite(final FieldInfo source, final FieldInfo dest, final Object from, final Object to) {
        try {
            dest.getWrite().invoke(to, source.getRead().invoke(from));
        } catch (final IllegalAccessException | InvocationTargetException exception) {
            log.error("Exception during read-write attempt from {} to {}", source.getRead(), dest.getWrite(), exception);
        }
    }

    /**
     * A special method that tries to write values from one object to another according to a specified fields list.
     *
     * @param source list of the fields to be applied to the source object
     * @param dest   list of the fields to be applied to the destination object
     * @param from   source object to be read
     * @param to     target object to be updated
     */
    static void copyValues(
            final Map<String, FieldInfo> source,
            final Map<String, FieldInfo> dest,
            final Object from,
            final Object to
    ) {
        dest.values().stream().filter(FieldInfo::isWritable)
                .forEach(info -> safeWrite(source.get(info.getName()), info, from, to));
    }
}
