package br.com.training.service;

import br.com.training.dto.UserDto;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired //inversao de controle injetando a interface
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto findByCpf(String cpf){
        Optional<User> obj = userRepository.findByCpf(cpf);
         User entity =obj.orElseThrow(() -> new ResourceNotFoundException("CPF nao encontrado"));
         return new UserDto(entity);
    }

}
