package br.com.training.dto;

public class UserDtoResponse {
    private String name;
    private String email;
    private String birthDate;


    public UserDtoResponse(){}

    public UserDtoResponse(String name , String email, String birthDate){
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public static UserDtoResponse castByDTO(UserDtoRequest user){
        return new UserDtoResponse(user.getName(),user.getEmail(), String.valueOf(user.getBirthDate()));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
