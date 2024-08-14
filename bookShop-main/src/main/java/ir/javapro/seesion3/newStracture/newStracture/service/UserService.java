package ir.javapro.seesion3.newStracture.service;

import ir.javapro.seesion3.newStracture.payload.dto.UserDto;
import ir.javapro.seesion3.newStracture.payload.filterDto.UserFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    UserDto edit(UserDto dto);
    UserDto findById(Long id);
    void delete(Long id);
    Page<UserDto> filter(UserFilterDto dto, Pageable pageable);

}
