
package com.example.springbootstudy.mapper;

import com.example.springbootstudy.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestUserMapper {

    //所有的查询条件，默认是 AND 和 = 关系，如果想在其他的关系，可以写相关的注解@OR ，或@Like
    TestUser selectTestUserById(Long id);


}