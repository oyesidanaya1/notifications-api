package org.yesid.notificationsapi.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageBody {
  @NotNull
  @NotEmpty
  private String category;

  @NotNull
  @NotEmpty
  private String message;
}
