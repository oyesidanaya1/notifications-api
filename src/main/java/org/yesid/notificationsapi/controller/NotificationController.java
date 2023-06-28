package org.yesid.notificationsapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yesid.notificationsapi.exception.CategoryException;
import org.yesid.notificationsapi.model.Category;
import org.yesid.notificationsapi.request.MessageBody;
import org.yesid.notificationsapi.service.NotificationService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping("notifications/publish")
  public void publish(@Valid @RequestBody MessageBody messageBody) {
    log.info("POST message received: {}", messageBody);
    var category = validateCategory(messageBody);
    notificationService.publishMessage(category, messageBody.getMessage());
  }

  protected Category validateCategory(MessageBody messageBody) {
    var categoryName = messageBody.getCategory().toUpperCase();
    try {
      return Category.valueOf(categoryName);
    } catch (IllegalArgumentException e) {
      throw new CategoryException("Invalid Category value: " + categoryName);
    }
  }
}
