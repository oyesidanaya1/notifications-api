package org.yesid.notificationsapi.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yesid.notificationsapi.model.NotificationType;
import org.yesid.notificationsapi.model.User;

@Slf4j
@Component
public class SmsChannel implements Channel {
  @Override
  public void sendMessage(User user, String message) {
    logMessage(user, message, log);
  }

  @Override
  public NotificationType getNotificationType() {
    return NotificationType.SMS;
  }
}
