package org.challenger.storageservice.web;

import lombok.RequiredArgsConstructor;
import org.challenger.common.dto.OrderDto;
import org.challenger.common.dto.UserDto;
import org.challenger.storageservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody final UserDto userDto) {
        return userService.save(userDto);
    }

    @GetMapping("/find-by-username")
    public UserDto findByUsername(@RequestParam final String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/find-by-id")
    public UserDto findById(@RequestParam final String id) {
        return userService.findById(id);
    }

    @PutMapping
    private UserDto update(@RequestBody final UserDto userDto) {
        return userService.update(userDto);
    }

    @GetMapping("/{id}/order/")
    public List<OrderDto> findAllOrdersByUserId(@RequestParam final String userId) {
        return userService.findAllOrdersByUserId(userId);
    }

}
