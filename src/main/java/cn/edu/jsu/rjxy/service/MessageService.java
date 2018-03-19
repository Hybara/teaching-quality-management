package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.MessageReaderDTO;
import cn.edu.jsu.rjxy.entity.vo.ReceiveMessage;
import cn.edu.jsu.rjxy.mappers.ReceiveMessageMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  @Autowired
  ReceiveMessageMapper receiveMessageMapper;

  public List<MessageReaderDTO> getMessagesByRecipientAndRecipientTypeAndFlag(long recipientId,
      String recipientType, String type, Integer page, Integer size) {
    Boolean flag = null;
    if ("noread".equals(type)) {
      flag = false;
    }
    List<ReceiveMessage> receiveMessages = receiveMessageMapper
        .getMessagesByRecipientAndRecipientType(recipientId, recipientType, flag, (page - 1) * size,
            size);

    List<MessageReaderDTO> messages = new ArrayList<>();
    for (ReceiveMessage receiveMessage : receiveMessages) {
      messages.add(new MessageReaderDTO(receiveMessage));
    }

    return messages;
  }

  public int getMessagesCountByRecipientAndRecipientTypeAndFlag(long recipientId,
      String recipientType, String type) {
    Boolean flag = null;
    if ("noread".equals(type)) {
      flag = false;
    }
    return receiveMessageMapper
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(recipientId, recipientType, flag);
  }

  public boolean readOneOwnMessage(long receiveMessageId) {
    return receiveMessageMapper.updateOneOwnMessageByReceiveMessageId(receiveMessageId);
  }

  public boolean readAllOwnMessages(long stuId, String recipientType) {
    return receiveMessageMapper
        .updateOwnMessagesFlagByRecipientAndRecipientType(stuId, recipientType);
  }

  public boolean deleteOneOwnMessageByReceiveMessageId(long receiveMessageId) {
    return receiveMessageMapper.deleteOneOwnMessageByReceiveMessageId(receiveMessageId);
  }

  public boolean deleteAllOwnMessage(long stuId, String recipientType, String deleteType) {
    if (deleteType == null) {
      return false;
    } else if ("all".equals(deleteType)) {
      return receiveMessageMapper
          .deleteOwnMessagesByRecipientAndRecipientType(stuId, recipientType);
    } else {
      return receiveMessageMapper
          .deleteOwnNoReadMessagesByRecipientAndRecipientType(stuId, recipientType);
    }
  }
}
