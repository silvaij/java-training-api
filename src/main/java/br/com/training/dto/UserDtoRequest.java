package br.com.training.dto;

import br.com.training.model.User;
import br.com.training.util.IsCpf;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

public class UserDtoRequest implements Serializable {

    @NotBlank(message ="Nome inválido")
    @Size(min=2,max=32)
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @IsCpf
    private String cpf;

    @NotNull
    private LocalDate birthDate;

    public UserDtoRequest(){}

    public UserDtoRequest(String name, String email, String cpf, LocalDate birthDate ){
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public UserDtoRequest(User user){
        name      = user.getName();
        email     = user.getEmail();
        cpf       = user.getCpf();
        birthDate = user.getBirthDate();
    }

    public User castUserDtobyUser(){
        return new User(name,email,cpf,birthDate);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}