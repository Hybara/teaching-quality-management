package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("teacherMessageController")
public class MessageController {

  @Autowired
  private MessageService messageService;

  private static final int NO_DATA = 0;
  private static final String MESSAGE_RECIPIENT_TYPE = "teacher";
  private static final String DEFAULT_MESSAGE_TYPE = "all";
  private static final int MESSAGE_PAGE_SIZE = 8;
  private static final int INDEX_PAGE_NUMBER = 1;


  @RequestMapping("/teacher/goMessage/{token}")
  public String goMessage(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "forward:/logout/" + token;
    }
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(teacher.getId(), MESSAGE_RECIPIENT_TYPE,
            DEFAULT_MESSAGE_TYPE);
    model.addAttribute("count",
        messageCount == NO_DATA ? NO_DATA : Math.ceil((double) messageCount / MESSAGE_PAGE_SIZE));
    return "/teacher/message";
  }

  @RequestMapping("/teacher/getMessages/{token}")
  @ResponseBody
  public Map<String, Object> getMessages(@PathVariable String token, HttpSession session,
      String type, Integer page) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(teacher.getId(), MESSAGE_RECIPIENT_TYPE,
            type);
    return JSONBaseUtil.structuralResponseMap(
        messageService
            .getMessagesByRecipientAndRecipientTypeAndFlag(teacher.getId(), MESSAGE_RECIPIENT_TYPE,
                type, page, MESSAGE_PAGE_SIZE),
        messageCount == NO_DATA ? NO_DATA : Math.ceil((double) messageCount / MESSAGE_PAGE_SIZE));
  }

  @RequestMapping("/teacher/readMessage")
  @ResponseBody
  public String readMessage(String token, long id, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    if (messageService.readOneOwnMessage(id)) {
      return "ok";
    } else {
      return "none";
    }
  }

  @RequestMapping("/teacher/readAllMessages")
  @ResponseBody
  public String readAllMessages(String token, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    if (messageService.readAllOwnMessages(teacher.getId(), MESSAGE_RECIPIENT_TYPE)) {
      return "ok";
    } else {
      return "none";
    }
  }

  @RequestMapping("/teacher/delMessage")
  @ResponseBody
  public String delMessage(String token, Long id,
      HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
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

  @RequestMapping("/teacher/delAllMessages")
  @ResponseBody
  public String delAllMessages(String token, String type, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    if (messageService.deleteAllOwnMessage(teacher.getId(), MESSAGE_RECIPIENT_TYPE, type)) {
      return "ok";
    } else {
      return "none";
    }
  }
}
