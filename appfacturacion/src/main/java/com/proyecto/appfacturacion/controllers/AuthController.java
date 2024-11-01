package com.proyecto.appfacturacion.controllers;


import com.proyecto.appfacturacion.dto.LoginRequest;
import com.proyecto.appfacturacion.dto.RegisterRequest;
import com.proyecto.appfacturacion.dto.UsuarioDTO;
import com.proyecto.appfacturacion.services.JwtService;
import com.proyecto.appfacturacion.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getContrasena())
            );
            UsuarioDTO usuario = usuarioService.findByEmail(loginRequest.getEmail());

            String token = jwtService.generateToken(usuario.getEmail());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (usuarioService.findByEmail(registerRequest.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está en uso");
            }
            UsuarioDTO nuevoUsuario = new UsuarioDTO();
            nuevoUsuario.setNombre(registerRequest.getNombre());
            nuevoUsuario.setApePaterno(registerRequest.getApePaterno());
            nuevoUsuario.setApeMaterno(registerRequest.getApeMaterno());
            nuevoUsuario.setEmail(registerRequest.getEmail());
            nuevoUsuario.setTelefono(registerRequest.getTelefono());
            nuevoUsuario.setContrasena(registerRequest.getContrasena());

            usuarioService.save(nuevoUsuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el usuario");
        }
    }
}
