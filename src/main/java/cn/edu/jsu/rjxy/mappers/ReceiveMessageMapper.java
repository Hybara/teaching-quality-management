package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ReceiveMessage;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReceiveMessageMapper {

  List<ReceiveMessage> getMessages(
      @Param("recipientId") long recipient, @Param("recipientType") String recipientType,
      @Param("flag") Boolean flag,
      @Param("step") Integer step, @Param("size") Integer size);

  int getMessagesCountByRecipientAndRecipientTypeAndFlag(@Param("recipientId") long recipient,
      @Param("recipientType") String recipientType, @Param("flag") Boolean flag);

  @Update("UPDATE receive_message SET flag=1 WHERE id=#{id}")
  boolean updateOneOwnMessageByReceiveMessageId(@Param("id") long receiveMessageId);

  @Update("UPDATE receive_message SET flag=1 WHERE recipient=#{recipientId} AND recipient_type=#{recipientType}")
  boolean updateOwnMessagesFlagByRecipientAndRecipientType(@Param("recipientId") long recipient,
      @Param("recipientType") String recipientType);

  @Delete("DELETE FROM receive_message WHERE id=#{id}")
  boolean deleteOneOwnMessageByReceiveMessageId(@Param("id") long receiveMessageId);

  @Delete("DELETE FROM receive_message WHERE recipient=#{recipientId} AND recipient_type=#{recipientType}")
  boolean deleteOwnMessagesByRecipientAndRecipientType(@Param("recipientId") long recipient,
      @Param("recipientType") String recipientType);

  @Delete("DELETE FROM receive_message WHERE recipient=#{recipientId} AND recipient_type=#{recipientType} AND flag=0")
  boolean deleteOwnNoReadMessagesByRecipientAndRecipientType(@Param("recipientId") long recipient,
      @Param("recipientType") String recipientType);
}
