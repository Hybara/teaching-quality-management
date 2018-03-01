package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.User;
import cn.edu.jsu.rjxy.mappers.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public List<User> getUserList() {
    return userMapper.findAll();
  }

}
