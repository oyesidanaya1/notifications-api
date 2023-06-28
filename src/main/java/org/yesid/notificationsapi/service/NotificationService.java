package org.yesid.notificationsapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yesid.notificationsapi.channel.Channel;
import org.yesid.notificationsapi.channel.ChannelFactory;
import org.yesid.notificationsapi.model.Category;
import org.yesid.notificationsapi.model.NotificationType;
import org.yesid.notificationsapi.model.User;
import org.yesid.notificationsapi.request.MessageBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final ChannelFactory channelFactory;
  private final UserService userService;
  public void publishMessage(Category category, String message) {

    userService.getUsersBySubscribedCategory(category).forEach(u -> {
      if (u.getChannels() != null) {
        u.getChannels().forEach(nt -> {
          var channel = channelFactory.getChannel(nt);
          channel.sendMessage(u, message);
        });
      }
    });
  }


}
