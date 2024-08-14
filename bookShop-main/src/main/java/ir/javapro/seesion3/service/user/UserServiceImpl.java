package ir.javapro.seesion3.service.user;

import ir.javapro.seesion3.dto.request.UserRequest;
import ir.javapro.seesion3.dto.response.UserResponse;
import ir.javapro.seesion3.exception.RuleException;
import ir.javapro.seesion3.model.User;
import ir.javapro.seesion3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        Optional<User> byUsername =
                userRepository.findByUsername(userRequest.getUsername());
        if(byUsername.isPresent())
            throw new RuleException("username.is.exist");
        return createUserResponse(userRepository.save(createUser(userRequest)));
    }

    private UserResponse createUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    private User createUser(UserRequest userRequest){
       return User.builder()
                .password(userRequest.getPassword())
                .username(userRequest.getUsername())
                .build();
    }
}
