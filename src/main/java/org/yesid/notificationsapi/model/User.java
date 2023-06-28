package org.yesid.notificationsapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
  private String id;
  private String name;
  private String email;
  private String phone;
  private List<Category> subscribed;
  private List<NotificationType> channels;
}
