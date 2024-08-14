package ir.javapro.seesion3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "{username.is.null}")
    @NotBlank(message = "{username.is.blank}")
    private final String username;
    @NotNull(message = "{password.is.null}")
    @NotBlank(message = "{password.is.blank}")
    private final String password;
}
