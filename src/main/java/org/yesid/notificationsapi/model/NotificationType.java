package org.yesid.notificationsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationType {
  SMS("SMS"),
  EMAIL("E-mail"),
  PUSH_NOTIFICATION("Push Notification");

  private String name;
}
