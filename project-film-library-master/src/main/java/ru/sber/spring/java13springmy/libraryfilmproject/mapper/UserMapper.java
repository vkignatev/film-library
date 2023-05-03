package ru.sber.spring.java13springmy.libraryfilmproject.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.DirectorDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.UserDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.model.Director;
import ru.sber.spring.java13springmy.libraryfilmproject.model.GenericModel;
import ru.sber.spring.java13springmy.libraryfilmproject.model.User;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.FilmRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.OrderInfoRepository;
import ru.sber.spring.java13springmy.libraryfilmproject.utils.DateFormatter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper
        extends GenericMapper<User, UserDTO> {
    private final OrderInfoRepository orderInfoRepository;
    private final ModelMapper modelMapper;

    protected UserMapper(ModelMapper modelMapper, OrderInfoRepository orderInfoRepository) {
        super(modelMapper, User.class, UserDTO.class);
        this.orderInfoRepository = orderInfoRepository;
        this.modelMapper =modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setOrdersIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(UserDTO.class, User.class)
                .addMappings(m -> m.skip(User::setOrder)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(User::setBirthDate)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getOrdersIds())) {
            destination.setOrder(new HashSet<>(orderInfoRepository.findAllById(source.getOrdersIds())));
        } else {
            destination.setOrder(Collections.emptySet());
        }
        destination.setBirthDate(DateFormatter.formatStringToDate(source.getBirthDate()));
    }

    @Override
    protected void mapSpecificFields(User source, UserDTO destination) {
        destination.setOrdersIds(Objects.isNull(source) || Objects.isNull(source.getOrder()) ? null
                : source.getOrder()
                .stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet()));

    }

    @Override
    protected Set<Long> getIds(User entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getOrder())
                ? null
                : entity.getOrder().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}

