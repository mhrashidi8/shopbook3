package ir.javapro.seesion3.newStracture.api;

import ir.swagger.v3.oas.annotations.Operation;
import ir.javapro.seesion3.newStracture.payload.dto.UserDto;
import ir.javapro.seesion3.newStracture.payload.filterDto.UserFilterDto;
import ir.javapro.seesion3.newStracture.service.UserService;
import ir.javapro.seesion3.newStracture.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookShop/v1/user")
public class userApi {


    private final UserService userService;

    @Operation(summary = "ایجاد کاربر جدید", description = "از طریق این سرویس می توانید کاربر جدید اضافه کنید")
    @PostMapping()
    public Response<UserDto> create(@RequestBody UserDto dto) {
        return Response.ok(userService.create(dto));
    }

    @Operation(summary = "Edit Users ", description = "This api edit user ")
    @PutMapping()
    public Response<UserDto> edit(@RequestBody UserDto dto) {
        return Response.ok(userService.edit(dto));
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable Long id) {
        userService.delete(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<UserDto> findById(@PathVariable Long id) {
        return Response.ok(userService.findById(id));
    }

    @PostMapping("/filter")
    public Response<Page<UserDto>> filter(@RequestBody UserFilterDto dto,
                                          @RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                          @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok(userService.filter(dto, pageable));
    }
}