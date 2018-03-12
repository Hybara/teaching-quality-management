package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.mappers.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

  @Autowired
  private AdminMapper adminMapper;

  public Admin getAdminById(long id) {
    return adminMapper.findById(id);
  }

}
