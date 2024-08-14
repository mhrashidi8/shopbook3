package ir.javapro.seesion3.service.shippingcard;

import ir.javapro.seesion3.dto.request.ShoppingCardRequest;
import ir.javapro.seesion3.dto.response.ShoppingCardResponse;
import ir.javapro.seesion3.exception.RuleException;
import ir.javapro.seesion3.model.*;
import ir.javapro.seesion3.repository.BookRepository;
import ir.javapro.seesion3.repository.FactorRepository;
import ir.javapro.seesion3.repository.ShoppingCardRepository;
import ir.javapro.seesion3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final ShoppingCardRepository shoppingCardRepository;
    private final FactorRepository factorRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    public ShoppingCardServiceImpl(ShoppingCardRepository shoppingCardRepository, FactorRepository factorRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.shoppingCardRepository = shoppingCardRepository;
        this.factorRepository = factorRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public ShoppingCardResponse addShoppingCard(ShoppingCardRequest shoppingCardRequest) {
        User user = userRepository.findById(shoppingCardRequest.getUserId())
                .orElseThrow(() -> new RuleException("user.not.exist"));
        Book book = bookRepository.findById(shoppingCardRequest.getBookId())
                .orElseThrow(() -> new RuleException("book.not.found"));
        Optional<Factor> byId = factorRepository.findByUserAndPayed(user, Payed.UNPAYED);
        Factor factor;
        factor = byId.orElseGet(() -> createFactor(user));
        factorRepository.save(factor);
        ShoppingCard shoppingCard = createShoppingCard(shoppingCardRequest,book,factor);
        return createShoppingCardResponse(shoppingCardRepository.save(shoppingCard));
    }

    private ShoppingCardResponse createShoppingCardResponse(ShoppingCard shoppingCard) {
        return ShoppingCardResponse.builder()
                .factorId(shoppingCard.getFactor().getId())
                .shoppingCard(shoppingCard.getId())
                .build();
    }

    private Factor createFactor(User user) {
      return  Factor.builder()
                .user(user)
                .payed(Payed.UNPAYED)
                .build();
    }

    private ShoppingCard createShoppingCard(ShoppingCardRequest shoppingCardRequest, Book book, Factor factor) {
       return ShoppingCard.builder()
                .book(book)
                .factor(factor)
                .count(shoppingCardRequest.getCount())
                .build();
    }
}
