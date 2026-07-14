package reviso_project.auth_service.dtos;

public record User (String userName,
                    String email,
                    String password,
                    String refreshToken
){
}
