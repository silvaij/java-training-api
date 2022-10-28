package br.com.training.service;

import br.com.training.dto.UserDtoRequest;
import br.com.training.model.User;
import br.com.training.repository.UserRepository;
import br.com.training.service.exception.ResourceNotFoundException;
import br.com.training.util.Validation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
public class UserService {

    @Autowired //inversao de controle injetando a interface
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByCpf(String cpf){
        Optional<User> obj = userRepository.findByCpf(cpf);
        return obj.orElseThrow(() -> new ResourceNotFoundException("CPF nao encontrado"));
    }

    @Transactional(readOnly = true)
    public User saveUser(UserDtoRequest dto){
           User user = dto.castUserDtobyUser();
        try{
            userRepository.save(user);
            return  user;
        }catch (DataIntegrityViolationException e){
           throw new ResourceNotFoundException("Erro ao inserir usuario "+ user.getName()+" Erro: "+ e);
        }
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User update(String cpf,User user){
        Validation.assertNotEmpty(user);
        Validation.assertNotEmpty(user.getCpf());
        User userDb = findByCpf(cpf);
        BeanUtils.copyProperties(user,userDb,"id");
        try{
          return userRepository.save(userDb);
        }catch (DataIntegrityViolationException e){
            throw new ResourceNotFoundException("Erro ao atualizar registro");
        }
    }

    @Transactional
    public void delete(String cpf){
        Long id = 1l;
        try{
            id = userRepository.findByCpf(cpf).orElseThrow().getId();
            userRepository.deleteById(id);
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Usuario nao encontrado");
        } catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("Usuario nao encontrado", id.intValue());
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Erro de integridade PK");
        }

    }

}


