package ir.javapro.seesion3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class BookRequest {

    @NotNull(message = "{book.name.is.null}")
    @NotBlank(message = "{book.name.is.blank}")
    private final String name;
    @NotNull(message = "{book.price.is.null}")
    @Min(value = 0 , message = "{price.min}")
    private final Long price;

}
