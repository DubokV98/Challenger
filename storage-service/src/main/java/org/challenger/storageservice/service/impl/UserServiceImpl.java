package org.challenger.storageservice.service.impl;

import lombok.AllArgsConstructor;
import org.challenger.common.dto.UserDto;
import org.challenger.storageservice.exception.UserNotFoundException;
import org.challenger.storageservice.repository.UserRepository;
import org.challenger.storageservice.service.UserService;
import org.challenger.storageservice.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(final UserDto userDto) {
        return userMapper.map(userRepository.save(userMapper.map(userDto)));
    }

    @Override
    public UserDto findByUsername(final String username) {
        return  userMapper.map(userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException.withUsername(username)));
    }

    @Override
    public UserDto findById(final String id) {
        return userMapper.map(userRepository.findById(id)
            .orElseThrow(UserNotFoundException.withId(id)));
    }
}
