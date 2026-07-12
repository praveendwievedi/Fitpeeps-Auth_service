package reviso_project.auth_service.dtos;

public record AuthResponse(
        String refreshToken,
        String accessToken
) {
}
