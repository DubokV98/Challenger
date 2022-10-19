package org.challenger.storageservice.service;

import org.challenger.common.dto.UserDto;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
public interface UserService {
    /**
     * Save user dto
     *
     * @param userDto - dto to save
     * @return saved dto
     */
    UserDto save(UserDto userDto);

    /**
     * Find by username
     *
     * @param username - username
     * @return user dto
     */
    UserDto findByUsername(String username);

    /**
     * Find by id
     *
     * @param id - id
     * @return user dto
     */
    UserDto findById(String id);
}
