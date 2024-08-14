package ir.javapro.seesion3.controller;

import ir.javapro.seesion3.dto.request.UserRequest;
import ir.javapro.seesion3.dto.response.UserResponse;
import ir.javapro.seesion3.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.ok(userService.save(userRequest));
    }
}
