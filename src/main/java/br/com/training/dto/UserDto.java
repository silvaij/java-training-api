package br.com.training.dto;

import br.com.training.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;


    public UserDto(){};

    public UserDto(Long id, String name, String email, String cpf, LocalDate birthDate ){
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public UserDto(User user){
        id        = user.getId();
        name      = user.getName();
        email     = user.getEmail();
        cpf       = user.getCpf();
        birthDate = user.getBirthDate();

    }
}
