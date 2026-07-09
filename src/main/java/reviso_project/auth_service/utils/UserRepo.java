package reviso_project.auth_service.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reviso_project.auth_service.dtos.User;

@FeignClient(name = "user-service")
public interface UserRepo {

    @GetMapping("/user/user-name")
    User getUserByUserName(@RequestBody String userName);

    @PostMapping("/user/refresh-token")
    User updateRefreshToken(@RequestBody String userName,@RequestBody String token);
}
