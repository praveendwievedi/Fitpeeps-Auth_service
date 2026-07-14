package reviso_project.auth_service.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reviso_project.auth_service.Configs.JwtService;
import reviso_project.auth_service.dtos.AuthRequest;
import reviso_project.auth_service.dtos.AuthResponse;
import reviso_project.auth_service.dtos.LoginRequest;
import reviso_project.auth_service.models.AuthToken;
import reviso_project.auth_service.repos.AuthTokenRepo;
import reviso_project.auth_service.utils.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final AuthTokenRepo authRepo;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetails customUserDetails;
    private final JwtService jwtService;

    private AuthResponse convertToAuthResponse(AuthToken authToken) {
        return new AuthResponse(
                authToken.getRefreshToken(),
                authToken.getAccessToken()
        );
    }

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

    public AuthResponse generateRefreshToken(AuthRequest request) {
        AuthToken authToken=new AuthToken();
        authToken.setUserName(request.userName());
        authToken.setRefreshToken(request.refreshToken());
        authToken.setAccessToken(request.accessToken());
        return convertToAuthResponse(authRepo.save(authToken));
    }

    public AuthResponse loginUser(LoginRequest request) {
        String userName= request.userName();
        AuthResponse response=login(request);
        AuthToken authToken=authRepo.findById(userName).get();
        authToken.setRefreshToken(response.refreshToken());
        authToken.setAccessToken(response.accessToken());
        return response;
    }

    public AuthResponse getRefreshToken(String userName) {
        return convertToAuthResponse(authRepo.findById(userName).get());
    }

}
