package org.yesid.notificationsapi.channel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.yesid.notificationsapi.model.NotificationType;

@Component
@RequiredArgsConstructor
public class ChannelFactory {

  private final EmailChannel emailChannel;
  private final SmsChannel smsChannel;
  private final PushNotificationChannel pushNotificationChannel;

  public Channel getChannel(NotificationType notificationType) {
    return switch (notificationType) {
      case SMS -> smsChannel;
      case EMAIL -> emailChannel;
      case PUSH_NOTIFICATION -> pushNotificationChannel;
    };
  }

}
