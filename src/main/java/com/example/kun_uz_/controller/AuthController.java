package com.example.kun_uz_.controller;


import com.example.kun_uz_.dto.AuthDTO;
import com.example.kun_uz_.dto.AuthResponseDTO;
import com.example.kun_uz_.dto.ProfileDTO;
import com.example.kun_uz_.dto.RegistrationDTO;
import com.example.kun_uz_.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping({"","/"})
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping({"/registration"})
    public ResponseEntity<?> registration(@RequestBody ProfileDTO dto){
        return ResponseEntity.ok(authService.registration(dto));
    }

}
