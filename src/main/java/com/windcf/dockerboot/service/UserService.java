package com.windcf.dockerboot.service;

import com.windcf.dockerboot.mapper.UserMapper;
import com.windcf.dockerboot.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chunf
 */
@Service
public class UserService {
    private final UserMapper userMapper;

    private final RedisTemplate<String, Serializable> redisTemplate;

    public static final String REDIS_PREFIX = "user:";

    public UserService(UserMapper userMapper, RedisTemplate<String, Serializable> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    public List<User> addUser() {
        List<User> ans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = User.builder().birth(LocalDate.now()).username("user" + i).build();
            if (userMapper.insertSelective(user) == 1) {
                redisTemplate.opsForValue().set(REDIS_PREFIX + user.getId(), user);
                ans.add(user);
            }
        }
        return ans;
    }

    public boolean delUser(long id) {
        int i = userMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            redisTemplate.delete(REDIS_PREFIX + id);
            return true;
        }

        return false;
    }

    public User getUser(Long id) {
        Serializable u = redisTemplate.opsForValue().get(REDIS_PREFIX + id);
        if (u == null) {
            User user = userMapper.selectByPrimaryKey(id);
            if (user != null) {
                redisTemplate.opsForValue().set(REDIS_PREFIX + user.getId(), user);
            }
            return user;
        }
        return (User) u;
    }
}
