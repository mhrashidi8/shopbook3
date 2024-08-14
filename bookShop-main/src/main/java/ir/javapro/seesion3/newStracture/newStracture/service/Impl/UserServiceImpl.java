package ir.javapro.seesion3.newStracture.service.Impl;

import ir.javapro.seesion3.exception.RuleException;
import ir.javapro.seesion3.model.User;
import ir.javapro.seesion3.newStracture.payload.converter.UserConverter;
import ir.javapro.seesion3.newStracture.payload.dto.UserDto;
import ir.javapro.seesion3.newStracture.payload.filterDto.UserFilterDto;
import ir.javapro.seesion3.newStracture.service.UserService;
import ir.javapro.seesion3.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository
            , UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto create(UserDto dto) {
        return saveCreate(dto);
    }

    private UserDto saveCreate(UserDto dto) {
        User user = userConverter.fromDtoToModel(dto);
        user.setId(null);
//        user.setPassword("");//TODO Encrypt password
        return userConverter.fromModelToDto(userRepository.save(user));
    }

    @Override
    public UserDto edit(UserDto dto) {

        if (isExists(dto.getUsername())) {
            throw new RuleException("username.is.exist");
        } else {
            return userConverter.fromModelToDto(userRepository.save(userConverter.fromDtoToModel(dto)));
        }


    }

    private boolean isExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDto findById(Long id) {
        return userConverter.fromModelToDto(userRepository.findById(id).orElseThrow(() -> new RuleException("username.is.exist")));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteFromUserByUserName(id);
    }

    @Override
    public Page<UserDto> filter(UserFilterDto dto, Pageable pageable) {

        return userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(root.get("userName"), "%"+dto.getUserName()+"%"));

//          predicates.add(criteriaBuilder.equal(root.get("isActive"),true));  TODO Check User IsActive And IsDeleted

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        },pageable).map(userConverter::fromModelToDto);
    }
}
