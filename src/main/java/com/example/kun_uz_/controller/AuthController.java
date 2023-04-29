package com.example.kun_uz_.controller;


import com.example.kun_uz_.dto.authDTO.RegistrationDTO;
import com.example.kun_uz_.dto.authDTO.RegistrationResponseDTO;
import com.example.kun_uz_.dto.authDTO.AuthDTO;
import com.example.kun_uz_.dto.authDTO.AuthResponseDTO;
import com.example.kun_uz_.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping({"","/"})
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDTO> registration(@RequestBody @Valid  RegistrationDTO dto) {
        return ResponseEntity.ok((RegistrationResponseDTO) authService.registration(dto));
    }

 /*   @GetMapping("/email/verification/{jwt}")
    public ResponseEntity<RegistrationResponseDTO> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok((RegistrationResponseDTO) authService.emailVerification(jwt));
    }*/

}
