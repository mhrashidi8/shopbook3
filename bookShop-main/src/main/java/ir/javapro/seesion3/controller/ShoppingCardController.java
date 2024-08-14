package ir.javapro.seesion3.controller;

import ir.javapro.seesion3.dto.request.ShoppingCardRequest;
import ir.javapro.seesion3.dto.response.ShoppingCardResponse;
import ir.javapro.seesion3.service.shippingcard.ShoppingCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/shoppingCard")
public class ShoppingCardController {

    private final ShoppingCardService shoppingCardService;

    public ShoppingCardController(ShoppingCardService shoppingCardService) {
        this.shoppingCardService = shoppingCardService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCardResponse> addBook(@RequestBody @Valid ShoppingCardRequest shoppingCardRequest){
      return ResponseEntity.ok(shoppingCardService.addShoppingCard(shoppingCardRequest));
    }
}
