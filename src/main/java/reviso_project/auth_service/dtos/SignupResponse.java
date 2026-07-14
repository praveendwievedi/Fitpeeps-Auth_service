package reviso_project.auth_service.dtos;

import java.time.LocalDate;

public record SignupResponse(
        String userName,
        String firstName,
        String lastName,
        String email,
        String password,
        String gender,
        LocalDate birthDate,
        String refreshToken
) {
}
