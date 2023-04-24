package com.broken.shopbackend.mapper;

import com.broken.shopbackend.domain.LoginForm;
import com.broken.shopbackend.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {

    @Select("select * from users where account = #{account} and password = #{password} and role = #{role};")
    User login(LoginForm form);

}




