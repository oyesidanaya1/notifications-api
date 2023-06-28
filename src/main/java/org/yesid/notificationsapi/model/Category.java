package org.yesid.notificationsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

  SPORTS("Sports"),
  FINANCE("Finance"),
  FILMS("Films");

  private String name;
}
