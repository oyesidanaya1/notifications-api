package org.yesid.notificationsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.yesid.notificationsapi.exception.ErrorHandler;
import org.yesid.notificationsapi.request.MessageBody;
import org.yesid.notificationsapi.service.NotificationService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ContextConfiguration(classes = {NotificationController.class})
class NotificationControllerTest {

  @MockBean
  private NotificationService notificationService;

  @Autowired
  private NotificationController notificationController;

  private MockMvc mvc;

  @BeforeEach
  void setup() {
    mvc =
        MockMvcBuilders.standaloneSetup(notificationController)
            .setControllerAdvice(new ErrorHandler())
            .alwaysDo(print())
            .build();
  }


  @Test
  void publishSuccessTest() throws Exception {
    var messageBody = MessageBody.builder()
            .category("sports")
            .message("a_message")
            .build();
    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    var jsonBody = mapper.writeValueAsString(messageBody);
    mvc.perform(
            post("/notifications/publish")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void publishInvalidMessageTest() throws Exception {
    var messageBody = MessageBody.builder()
        .category("sports")
        .message("")
        .build();
    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    var jsonBody = mapper.writeValueAsString(messageBody);
    mvc.perform(
            post("/notifications/publish")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }

  @Test
  void publishInvalidCategoryTest() throws Exception {
    var messageBody = MessageBody.builder()
        .category("invalid")
        .message("a message")
        .build();
    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    var jsonBody = mapper.writeValueAsString(messageBody);
    mvc.perform(
            post("/notifications/publish")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(
            result ->
                assertEquals(
                    "Invalid Category value: INVALID",
                    result.getResolvedException().getMessage()));
  }
}
