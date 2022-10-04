package org.challenger.common.mapper;

import lombok.SneakyThrows;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A bean that pre-defines all necessary for mapping info.
 *
 * @param <E> type that marked to be considered as Entity
 * @param <D> type that marked to be considered as DTO
 * @author u.dubok
 * @since 10/04/2022
 */
class ConfigInitializer<E extends MappedEntity, D extends MappedDto> {

    private static final int ARG_ENTITY = 0;
    private static final int ARG_DTO = 1;
    private static final Collection<String> EXCLUDED_FIELD_ANNOTATIONS = Collections.singletonList(
        "javax.persistence.GeneratedValue"
    );
    private static final Predicate<Field> MAPPABLE_FIELDS_PREDICATE = field ->
        !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers());

    private final Class<E> classEntity;
    private final Class<D> classDto;
    private final Map<String, FieldInfo> fieldsEnt = new HashMap<>();
    private final Map<String, FieldInfo> fieldsDto = new HashMap<>();

    /**
     * A constructor, that retrieves info about requesting class and starts initialization of the types information.
     *
     * @param mapperClass      source requested class
     * @param exclusionsEntity list of the configured custom exclusions for {@link MappedEntity}
     * @param exclusionsDto    list of the configured custom exclusion for {@link MappedDto}
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected ConfigInitializer(
        final Class<? extends Mapper> mapperClass,
        final Collection<String> exclusionsEntity,
        final Collection<String> exclusionsDto
    ) {
        final Type[] arg = ((ParameterizedType) mapperClass.getGenericSuperclass()).getActualTypeArguments();
        classEntity = (Class<E>) arg[ARG_ENTITY];
        classDto = (Class<D>) arg[ARG_DTO];
        initializeFieldsConfiguration(exclusionsEntity, exclusionsDto);
    }

    /**
     * A special method that collects all fields and prepares them to be used later. Step by step actions:
     * - read {@link MappedEntity}: collect fields and search for {@code org.springframework.data.annotation.Id} to be excluded
     * - read {@link MappedDto}: collect fields
     * - cleanup field list from fields that haven't the pair in the other mapped type or have different types
     * - add custom exclusions to the lists
     * - calls a simple method that converts everything into an optimized structure
     *
     * @param entity list of exclusions for entity
     * @param dto    list of exclusions for dto
     * @throws NullPointerException in case any of the arguments is null
     */
    private void initializeFieldsConfiguration(final Collection<String> entity, final Collection<String> dto) {

        final List<String> exclusionsEntity = new LinkedList<>();
        final List<String> exclusionsDto = new LinkedList<>();

        final Map<String, Field> entityFields = collectFieldsToMap(classEntity, exclusionsEntity, true);
        final Map<String, Field> dtoFields = collectFieldsToMap(classDto, exclusionsDto, false);

        cleanUpFieldListFromNotMappableFields(entityFields, dtoFields);

        // read requested exclusions
        entity.stream().filter(entityFields::containsKey).forEach(exclusionsEntity::add);
        dto.stream().filter(dtoFields::containsKey).forEach(exclusionsDto::add);

        // write optimized lists
        convertToReadsWrites(classEntity, entityFields.keySet(), exclusionsEntity, fieldsEnt);
        convertToReadsWrites(classDto, dtoFields.keySet(), exclusionsDto, fieldsDto);
    }

    /**
     * A simple converter of field list and its details into {@link FieldInfo} entries and puts them into the result
     * list. Also defines if the field excluded from write operations.
     *
     * @param type       class that will be used for {@link PropertyDescriptor} definition
     * @param fields     list of the fields to be converted
     * @param exclusions list of excluded from write operations fields
     * @param targets    container for the results
     */
    private void convertToReadsWrites(
        final Class<?> type,
        final Collection<String> fields,
        final Collection<String> exclusions,
        final Map<String, FieldInfo> targets
    ) {
        fields.forEach(field -> {
            final PropertyDescriptor descriptor = sneakyPropertyDescriptor(field, type);
            final Method readMethod = descriptor.getReadMethod();
            readMethod.setAccessible(true);
            final Method writeMethod = descriptor.getWriteMethod();
            writeMethod.setAccessible(true);
            targets.put(field, new FieldInfo(field, readMethod, writeMethod, !exclusions.contains(field)));
        });
    }

    /**
     * Helper method that hides possible exceptions with the generation of the {@link PropertyDescriptor}.
     *
     * @param field name of the field to be described
     * @param type  class name from where it should be described
     * @return a complete descriptor of the specified field
     */
    @SneakyThrows
    private PropertyDescriptor sneakyPropertyDescriptor(final String field, final Class<?> type) {
        return new PropertyDescriptor(field, type);
    }

    /**
     * The method, that validates passed annotation list for any parameters to be excluded.
     *
     * @param annotations array with annotations
     * @return true in case if field must be marked as excluded
     */
    private boolean isExcludedAnnotationPresent(final Annotation[] annotations) {
        return Arrays.stream(annotations)
            .map(Annotation::annotationType)
            .map(Class::getName)
            .map(EXCLUDED_FIELD_ANNOTATIONS::contains)
            .filter(Boolean::booleanValue)
            .findAny()
            .orElse(false);
    }

    /**
     * The helper method that collects all fields from the specified class.
     *
     * @param clz                to be analyzed
     * @param exclusions         link to exclusions collection
     * @param analyzeAnnotations flag that enables check for annotations
     * @return mapped by their names fields
     */
    private Map<String, Field> collectFieldsToMap(
        final Class<?> clz,
        final List<String> exclusions,
        final boolean analyzeAnnotations) {
        return Stream.of(clz.getDeclaredFields()).filter(MAPPABLE_FIELDS_PREDICATE).collect(Collectors.toMap(Field::getName, field -> {
            if (analyzeAnnotations && isExcludedAnnotationPresent(field.getAnnotations())) {
                exclusions.add(field.getName());
            }
            return field;
        }));
    }

    /**
     * The helper method that anylyze all the fields and removes those that can't be mapped automatically.
     *
     * @param entityFields map with the fields of entity
     * @param dtoFields    map with the fields of dto
     */
    private void cleanUpFieldListFromNotMappableFields(
        final Map<String, Field> entityFields,
        final Map<String, Field> dtoFields
    ) {
        Stream.of(entityFields.keySet(), dtoFields.keySet()).flatMap(Collection::stream).distinct()
            .filter(name -> isFieldNotMappable(entityFields, dtoFields, name))
            // prevent ConcurrentModificationException
            .collect(Collectors.toList()).forEach(name -> {
                entityFields.remove(name);
                dtoFields.remove(name);
            });
    }

    /**
     * The helper method that applies 3 checks:
     * - is the mentioned field missing in entity
     * - is the mentioned field missing in dto
     * - is these fields have different types
     *
     * @param entityFields map with the fields of entity
     * @param dtoFields    map with the fields of dto
     * @param name         name of the field to be validated
     * @return true if any of mentioned conditions is true
     */
    private boolean isFieldNotMappable(final Map<String, Field> entityFields, final Map<String, Field> dtoFields, final String name) {
        return !entityFields.containsKey(name) || !dtoFields.containsKey(name)
            || !Objects.equals(entityFields.get(name).getGenericType(), dtoFields.get(name).getGenericType());
    }

    Class<E> getClassEntity() {
        return classEntity;
    }

    Class<D> getClassDto() {
        return classDto;
    }

    Map<String, FieldInfo> getFieldsEnt() {
        return fieldsEnt;
    }

    Map<String, FieldInfo> getFieldsDto() {
        return fieldsDto;
    }
}
