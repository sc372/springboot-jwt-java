package sc372.springbootjwtjava.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String userEmail;
    private String password;
}