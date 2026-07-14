package reviso_project.auth_service.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reviso_project.auth_service.dtos.SignupResponse;
import reviso_project.auth_service.dtos.User;

@FeignClient(name = "user-service")
public interface UserRepo {

    @GetMapping("/user/{userName}")
    User getUserByUserName(@PathVariable("userName") String userName);

    @GetMapping("/user/{userName}/refresh-token")
    User getRefreshToken(@PathVariable("userName") String userName);

    @PostMapping("/user/{userName}/refresh-token")
    User updateRefreshToken(@PathVariable("userName") String userName,@RequestBody String refreshToken);

    @PostMapping("/user/register")
    User registerUser(@RequestBody SignupResponse registerRequest);
}
