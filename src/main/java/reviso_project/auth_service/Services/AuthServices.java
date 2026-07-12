package reviso_project.auth_service.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reviso_project.auth_service.Configs.JwtService;
import reviso_project.auth_service.dtos.AuthResponse;
import reviso_project.auth_service.dtos.LoginRequest;
import reviso_project.auth_service.utils.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetails customUserDetails;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );
        UserDetails userDetails = customUserDetails.loadUserByUsername(request.userName());
        String accessToken=jwtService.buildTokens(userDetails,0);
        String refreshToken=jwtService.buildTokens(userDetails,1);
        return new AuthResponse(accessToken,refreshToken);
    }
}
