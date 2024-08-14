package ir.javapro.seesion3.service.shippingcard;

import ir.javapro.seesion3.dto.request.ShoppingCardRequest;
import ir.javapro.seesion3.dto.response.ShoppingCardResponse;

public interface ShoppingCardService {

    ShoppingCardResponse addShoppingCard(ShoppingCardRequest shoppingCardRequest);
}
