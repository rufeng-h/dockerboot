package com.windcf.dockerboot.controller;

import com.windcf.dockerboot.model.User;
import com.windcf.dockerboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author : chunf
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public List<User> addUser() {
        return userService.addUser();
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delUser(@PathVariable Long id) {
        if (userService.delUser(id)) {
            return Collections.singletonMap("succ", true);
        } else {
            return Collections.singletonMap("succ", false);
        }
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}
