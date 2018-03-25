package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

  @Autowired
  private MessageService messageService;

  private static final int NO_DATA = 0;
  private static final int INDEX_PAGE_NUMBER = 1;
  private static final String MESSAGE_RECIPIENT_TYPE = "student";
  private static final int MESSAGE_PAGE_SIZE = 8;

  @RequestMapping("/student/getMessages/{token}")
  @ResponseBody
  public Map<String, Object> getMessages(@PathVariable String token, HttpSession session,
      String type, Integer page) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            type);
    return JSONBaseUtil.structuralResponseMap(
        messageService
            .getMessagesByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
                type, page, MESSAGE_PAGE_SIZE),
        messageCount == NO_DATA ? NO_DATA : Math.ceil((double) messageCount / MESSAGE_PAGE_SIZE));
  }

  @RequestMapping("/student/readMessage")
  @ResponseBody
  public String readMessage(String token, long id, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (messageService.readOneOwnMessage(id)) {
      return "ok";
    } else {
      return "none";
    }
  }

  @RequestMapping("/student/readAllMessages")
  @ResponseBody
  public String readAllMessages(String token, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (messageService.readAllOwnMessages(student.getId(), MESSAGE_RECIPIENT_TYPE)) {
      return "ok";
    } else {
      return "none";
    }
  }

  @RequestMapping("/student/delMessage")
  @ResponseBody
  public String delMessage(String token, Long id,
      HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (id == null) {
      return "none";
    }
    if (messageService.deleteOneOwnMessageByReceiveMessageId(id)) {
      return "ok";
    } else {
      return "none";
    }
  }

  @RequestMapping("/student/delAllMessages")
  @ResponseBody
  public String delAllMessages(String token, String type, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (messageService.deleteAllOwnMessage(student.getId(), MESSAGE_RECIPIENT_TYPE, type)) {
      return "ok";
    } else {
      return "none";
    }
  }
}
