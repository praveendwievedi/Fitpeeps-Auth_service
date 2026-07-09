package reviso_project.auth_service.dtos;

public record LoginRequest(
        String userName,
        String password
) {
}
