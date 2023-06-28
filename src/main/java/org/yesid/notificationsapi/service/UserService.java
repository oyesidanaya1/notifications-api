package org.yesid.notificationsapi.service;

import org.springframework.stereotype.Service;
import org.yesid.notificationsapi.model.Category;
import org.yesid.notificationsapi.model.NotificationType;
import org.yesid.notificationsapi.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

  private List<User> allUsers;

  public List<User> getUsersBySubscribedCategory(final Category category) {
    return getAllUsers().stream()
        .filter(user -> user.getSubscribed() != null && user.getSubscribed().contains(category))
        .toList();
  }

  public List<User> getAllUsers() {
    if (allUsers == null) {
      allUsers = new ArrayList<>();
      Random random = new Random();
      var size = random.nextInt(20) + 10 ;
      for(var i=0; i<size; i++) {
        var userId = i + 1;
        List<Category> categories = getMockedCategories(userId);
        List<NotificationType> notificationTypes = getMockedNotificationTypes(userId);

        var user = User.builder()
            .id(String.valueOf(userId))
            .name("User " + userId)
            .email("user"+ userId +"@test.com")
            .phone("+155556664" + userId)
            .subscribed(categories)
            .channels(notificationTypes)
            .build();
        allUsers.add(user);
      }
    }
    return allUsers;
  }

  private static List<Category> getMockedCategories(final int userId) {
    return switch (userId % 8) {
      case 0 -> Arrays.asList(Category.SPORTS);
      case 1 -> Arrays.asList(Category.FINANCE);
      case 2 -> Arrays.asList(Category.FILMS);
      case 3 -> Arrays.asList(Category.SPORTS, Category.FINANCE);
      case 4 -> Arrays.asList(Category.SPORTS, Category.FILMS);
      case 5 -> Arrays.asList(Category.FINANCE, Category.FILMS);
      case 6 -> Arrays.asList(Category.SPORTS, Category.FINANCE, Category.FILMS);
      default -> null;
    };
  }

  private static List<NotificationType> getMockedNotificationTypes(final int userId) {
    return switch (userId % 6) {
      case 0 -> Arrays.asList(NotificationType.SMS);
      case 1 -> Arrays.asList(NotificationType.EMAIL);
      case 2 -> Arrays.asList(NotificationType.PUSH_NOTIFICATION);
      case 3 -> Arrays.asList(NotificationType.SMS, NotificationType.EMAIL);
      case 4 -> Arrays.asList(NotificationType.SMS, NotificationType.PUSH_NOTIFICATION);
      case 5 -> Arrays.asList(NotificationType.EMAIL, NotificationType.PUSH_NOTIFICATION);
      case 6 -> Arrays.asList(NotificationType.SMS, NotificationType.EMAIL, NotificationType.PUSH_NOTIFICATION);
      default -> null;
    };
  }


}
