package ru.sber.spring.java13springmy.libraryfilmproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.RoleDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.UserDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.mapper.UserMapper;
import ru.sber.spring.java13springmy.libraryfilmproject.model.User;
import ru.sber.spring.java13springmy.libraryfilmproject.repository.UserRepository;

@Service
@Slf4j
public class UserService extends GenericService<User, UserDTO> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
       // log.info(userDTO.getFirstName());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
       // log.info(roleDTO.toString());

        userDTO.setRoles(roleDTO);
       // log.info(userDTO.getRoles().toString());

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
      // log.info(userDTO.getRoles().toString());

       // log.info(userDTO.toString());

        return mapper.toDTO(repository.save(mapper.toEntity(userDTO)));
    }
}

