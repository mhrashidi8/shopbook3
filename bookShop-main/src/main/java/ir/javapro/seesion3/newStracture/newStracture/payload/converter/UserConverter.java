package ir.javapro.seesion3.newStracture.payload.converter;

import ir.javapro.seesion3.model.User;
import ir.javapro.seesion3.newStracture.payload.dto.UserDto;
import org.mapstruct.*;
import org.springframework.util.StringUtils;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,componentModel = "spring")
public interface UserConverter {
    UserDto fromModelToDto(User user);
    User fromDtoToModel(UserDto dto);
    @AfterMapping
    default void afterFromModelToDto(UserDto dto , @MappingTarget User user){
        if(StringUtils.hasText(dto.getPassword())){
            user.setPassword("-");
        }
    }
}
