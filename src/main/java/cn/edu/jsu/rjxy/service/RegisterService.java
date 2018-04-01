package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.mappers.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterService {

  @Autowired
  private RegisterMapper registerMapper;

  public Register getById(long id) {
    return registerMapper.getById(id);
  }

  public Register getLoginer(String account, String password) {
    return registerMapper.getByNumberAndPassword(account, password);
  }

  public boolean setPassword(long id, String password) {
    if (password == null) {
      return false;
    }
    return registerMapper.updatePassword(id, password);
  }

  public boolean setHeader(long id, String header) {
    if (header == null) {
      return false;
    }
    return registerMapper.updateHeader(id, header);
  }

}
