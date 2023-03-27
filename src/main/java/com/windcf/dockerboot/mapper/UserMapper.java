package com.windcf.dockerboot.mapper;

import com.windcf.dockerboot.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author : chunf
*/
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}