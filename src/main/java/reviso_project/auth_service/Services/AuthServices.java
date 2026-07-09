package reviso_project.auth_service.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reviso_project.auth_service.Configs.JwtService;
import reviso_project.auth_service.utils.UserRepo;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final UserRepo userRepo;

    private final JwtService jwtService;

//    public UserDetails fillCustomUserDetails(User user){
//
//    }


}
