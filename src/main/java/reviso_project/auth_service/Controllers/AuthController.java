package reviso_project.auth_service.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviso_project.auth_service.dtos.LoginRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(){
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
