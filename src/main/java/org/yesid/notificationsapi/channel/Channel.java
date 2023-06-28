package org.yesid.notificationsapi.channel;

import org.slf4j.Logger;
import org.yesid.notificationsapi.model.NotificationType;
import org.yesid.notificationsapi.model.User;


public interface Channel {
  void sendMessage(User user, String message);

  NotificationType getNotificationType();

  default void logMessage(User user, String message, Logger log) {
    log.info("Notification message sent! Type: {}, message: {}, user: {}", getNotificationType(),  message, user);
  }
}
