package reviso_project.auth_service.Configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter {
    private final JwtService jwtService;


}
