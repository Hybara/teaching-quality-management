package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Metadata;
import cn.edu.jsu.rjxy.mappers.MetadataMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

  @Autowired
  MetadataMapper metadataMapper;

  public List<Metadata> getAllMetadata() {
    return metadataMapper.getAll();
  }

  public boolean updateMetadatas(List<String> metadatas) {

    if (checkMetadatas(metadatas)) {
      Map<String, String> metadatasMap = new HashMap<>(metadatas.size());
      for (int i=0; i<metadatas.size(); i++) {
        metadatasMap.put((i+1)+"", metadatas.get(i));
      }
      return metadataMapper.updateMetadatas(metadatasMap);
    } else {
      return false;
    }
  }

  public boolean checkMetadatas(List<String> metadatas) {
    if (Integer.parseInt(metadatas.get(0))<=Integer.parseInt(metadatas.get(1))) {
      return false;
    }
    if (Integer.parseInt(metadatas.get(1))<=Integer.parseInt(metadatas.get(2))) {
      return false;
    }
    if (Integer.parseInt(metadatas.get(2))<=Integer.parseInt(metadatas.get(3))) {
      return false;
    }


    if (Integer.parseInt(metadatas.get(4))<=Integer.parseInt(metadatas.get(5))) {
      return false;
    }
    if (Integer.parseInt(metadatas.get(5))<=Integer.parseInt(metadatas.get(6))) {
      return false;
    }
    if (Integer.parseInt(metadatas.get(6))<=Integer.parseInt(metadatas.get(7))) {
      return false;
    }

    if (Integer.parseInt(metadatas.get(8))<=0 || Integer.parseInt(metadatas.get(8))>30) {
      return false;
    }
    if (Integer.parseInt(metadatas.get(9))<=0 || Integer.parseInt(metadatas.get(9))>30) {
      return false;
    }

    return true;
  }
}
