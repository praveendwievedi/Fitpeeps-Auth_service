package reviso_project.auth_service.dtos;

public record AuthRequest(
        String userName,
        String refreshToken,
        String accessToken
) {
}
