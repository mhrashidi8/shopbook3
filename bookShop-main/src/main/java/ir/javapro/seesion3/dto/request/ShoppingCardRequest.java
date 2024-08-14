package ir.javapro.seesion3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ShoppingCardRequest {

    @NotNull(message = "{user.id.is.null}")
    private Long userId;
    @NotNull(message = "{book.id.is.null}")

    private Long bookId;
    @NotNull(message = "{count.id.is.null}")
    @Min(value = 1, message = "{count.not.valid}")
    private Integer count;
}
