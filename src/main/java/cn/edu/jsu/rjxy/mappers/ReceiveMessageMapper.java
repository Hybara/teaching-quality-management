package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ReceiveMessage;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReceiveMessageMapper {

  List<ReceiveMessage> getMessagesByRecipientAndRecipientType(
      @Param("recipientId") long recipient, @Param("recipientType") String recipientType,
      @Param("flag") Boolean flag,
      @Param("step") Integer step, @Param("size") Integer size);

  int getMessagesCountByRecipientAndRecipientTypeAndFlag(@Param("recipientId") long recipient,
      @Param("recipientType") String recipientType, @Param("flag") Boolean flag);
}
