package reviso_project.auth_service.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reviso_project.auth_service.dtos.User;
import reviso_project.auth_service.utils.UserRepo;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.getUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found" + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.userName())
                .password(user.password())
                .authorities("ROLE_USER")
                .build();
    }
}
