package reviso_project.auth_service.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import reviso_project.auth_service.Services.AuthServices;
import reviso_project.auth_service.dtos.AuthRequest;
import reviso_project.auth_service.dtos.AuthResponse;
import reviso_project.auth_service.dtos.LoginRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServices authServices;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request){
        AuthResponse response=authServices.loginUser(request);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/new-user")
    public ResponseEntity<?> generateRefreshToken(@RequestBody LoginRequest request ){
        AuthResponse authResponse=authServices.login(request);
        AuthRequest request1=new AuthRequest(request.userName(),authResponse.refreshToken(),authResponse.accessToken());
        AuthResponse authResponse1=authServices.generateRefreshToken(request1);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/{userName}/refresh-token")
    public ResponseEntity<?> getRefreshToken(@PathVariable String userName){
        AuthResponse response=authServices.getRefreshToken(userName);
        return  new ResponseEntity<>(response.refreshToken(),HttpStatus.OK);
    }

    @GetMapping("/{userName}/access-token")
    public ResponseEntity<?> getAccessToken(@PathVariable String userName){
        AuthResponse response=authServices.getRefreshToken(userName);
        return  new ResponseEntity<>(response.accessToken(),HttpStatus.OK);
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(){
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
