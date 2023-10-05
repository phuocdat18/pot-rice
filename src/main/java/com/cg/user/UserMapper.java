package com.cg.user;

import com.cg.model.User;
import com.cg.user.dto.BaseUser;
import com.cg.user.dto.UserResult;
import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;

@Component
public class UserMapper extends BaseMapper<UserResult, User, BaseUser> {

}
