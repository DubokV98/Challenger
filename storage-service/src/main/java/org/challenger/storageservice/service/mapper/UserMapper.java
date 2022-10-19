package org.challenger.storageservice.service.mapper;

import org.challenger.common.dto.UserDto;
import org.challenger.common.mapper.Mapper;
import org.challenger.storageservice.model.User;
import org.springframework.stereotype.Component;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Component
public class UserMapper extends Mapper<User, UserDto> {
}
