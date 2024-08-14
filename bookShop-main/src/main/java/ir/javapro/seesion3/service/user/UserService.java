package ir.javapro.seesion3.service.user;

import ir.javapro.seesion3.dto.request.UserRequest;
import ir.javapro.seesion3.dto.response.UserResponse;

public interface UserService {

    UserResponse save(UserRequest userRequest);
}
