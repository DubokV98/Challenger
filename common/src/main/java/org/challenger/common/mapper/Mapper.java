package org.challenger.common.mapper;

import static org.challenger.common.mapper.MapperUtils.copyValues;
import static org.challenger.common.mapper.MapperUtils.instantiate;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * A special mapper that pre-loads all necessary info about the types. Mostly used as a lightweight alternative to
 * existing solutions like MapStruct or ObjectsMapper. The cache implemented to speed up conversions, during the runtime
 * there no need to read class information and retrieve access to the fields.
 * <p/>
 * The mapper allows configuring exclusions and customizes conversion with the custom code.
 * <p/>
 * NOTICE:
 * - By default, any field marked with annotation {@code org.springframework.data.annotation.Id} will be ignored during
 * conversion to the {@link MappedEntity}.
 * - Own exclusions could be defined with {@link #getDtoExclusions()} or {@link #getEntityExclusions()}.
 * - Any fields with the same names, but different types will be also ignored.
 * - To define the way how they should be mapped override {@link #customConversion(MappedDto, MappedEntity)} or
 * {@link #customConversion(MappedEntity, MappedDto)}.
 *
 * @param <E> type that marked to be considered as Entity
 * @param <D> type that marked to be considered as DTO
 * @author u.dubok
 * @since 10/04/2022
 */
@Slf4j
public abstract class Mapper<E extends MappedEntity, D extends MappedDto> {

    private final Class<E> classEnt;
    private final Class<D> classDto;
    private final Map<String, FieldInfo> fieldsEnt;
    private final Map<String, FieldInfo> fieldsDto;

    /**
     * Default constructor. Should be called before any call to the {@code map} method.
     */
    public Mapper() {
        log.debug("Initializing of {}", this.getClass());

        final ConfigInitializer<E, D> cfg =
            new ConfigInitializer<>(this.getClass(), getEntityExclusions(), getDtoExclusions());

        classEnt = cfg.getClassEntity();
        classDto = cfg.getClassDto();
        fieldsEnt = Collections.unmodifiableMap(cfg.getFieldsEnt());
        fieldsDto = Collections.unmodifiableMap(cfg.getFieldsDto());

        log.debug("Finished initializing of {}", this.getClass());
    }

    /**
     * A special method that allows configuring the fields that should not be copied during the mapping to
     * {@link MappedEntity}. Any fields mentioned in the list will be ignored during the write operations, if provide
     * incorrect field name - it will be ignored
     *
     * @return list of field names to be ignored
     * @throws NullPointerException if the provided return value is null
     */
    public Collection<String> getEntityExclusions() {
        return Collections.emptyList();
    }

    /**
     * A special method that allows configuring the fields that should not be copied during the mapping to
     * {@link MappedDto}. Any fields mentioned in the list will be ignored during the write operations, if provide
     * incorrect field name - it will be ignored
     *
     * @return list of field names to be ignored
     * @throws NullPointerException if the provided return value is null
     */
    public Collection<String> getDtoExclusions() {
        return Collections.emptyList();
    }

    /**
     * The method could be used in the case when need to use complex conversion or define a custom way of conversion.
     *
     * @param from source object
     * @param to   target object after the conversion
     */
    public void customConversion(final E from, final D to) {
        // override if needed
    }

    /**
     * The method could be used in the case when need to use complex conversion or define a custom way of conversion.
     *
     * @param from source object
     * @param to   target object after the conversion
     */
    public void customConversion(final D from, final E to) {
        // override if needed
    }

    /**
     * Method, that performs the conversion to specified type.
     *
     * @param from source object
     * @return target object
     */
    public final E map(final D from) {
        final E to = instantiate(classEnt);
        map(from, to);
        return to;
    }

    /**
     * Method, that performs the conversion to specified type.
     *
     * @param from source object
     * @return target object
     */
    public final D map(final E from) {
        final D to = instantiate(classDto);
        map(from, to);
        return to;
    }

    /**
     * Method, that performs the update based on specific object.
     *
     * @param from source object
     * @param to   target object
     * @return result object
     */
    public final D map(final E from, final D to) {
        copyValues(fieldsEnt, fieldsDto, from, to);
        customConversion(from, to);
        return to;
    }

    /**
     * Method, that performs the update based on specific object.
     *
     * @param from source object
     * @param to   target object
     * @return result object
     */
    public final E map(final D from, final E to) {
        copyValues(fieldsDto, fieldsEnt, from, to);
        customConversion(from, to);
        return to;
    }
}
