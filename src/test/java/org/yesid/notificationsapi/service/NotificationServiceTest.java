package org.yesid.notificationsapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.yesid.notificationsapi.channel.Channel;
import org.yesid.notificationsapi.channel.ChannelFactory;
import org.yesid.notificationsapi.model.Category;
import org.yesid.notificationsapi.model.NotificationType;
import org.yesid.notificationsapi.model.User;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {NotificationService.class})
class NotificationServiceTest {
  @MockBean
  private ChannelFactory channelFactory;
  @MockBean
  private UserService userService;

  @Autowired
  private NotificationService notificationService;

  @MockBean
  private Channel channel;

  @BeforeEach
  void setup() {
    var user = User.builder()
        .subscribed(Arrays.asList(Category.SPORTS))
        .channels(Arrays.asList(NotificationType.SMS))
        .build();
    var users = Arrays.asList(user);
    when(userService.getUsersBySubscribedCategory(Category.SPORTS)).thenReturn(users);
    when(channelFactory.getChannel(any())).thenReturn(channel);
  }

  @Test
  void publishSuccessTest() {
    notificationService.publishMessage(Category.SPORTS, "A message");
    verify(userService, times(1)).getUsersBySubscribedCategory(Category.SPORTS);
    verify(channelFactory, times(1)).getChannel(any());
    verify(channel, times(1)).sendMessage(any(), eq("A message"));
  }

  @Test
  void publishNoMessageTest() {
    notificationService.publishMessage(Category.FILMS, "A message");
    verify(userService, times(1)).getUsersBySubscribedCategory(Category.FILMS);
    verifyNoInteractions(channelFactory);
    verifyNoInteractions(channel);
  }
}
