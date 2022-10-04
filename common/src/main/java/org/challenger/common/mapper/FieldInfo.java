package org.challenger.common.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @author u.dubok
 * @since 10/04/2022
 */
@Getter
@AllArgsConstructor
class FieldInfo {
    private final String name;
    private final Method read;
    private final Method write;
    private final boolean writable;
}
