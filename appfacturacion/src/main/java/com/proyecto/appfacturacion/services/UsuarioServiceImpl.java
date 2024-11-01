package com.proyecto.appfacturacion.services;

import com.proyecto.appfacturacion.dto.RegistrarUsuarioDTO;
import com.proyecto.appfacturacion.dto.UsuarioDTO;
import com.proyecto.appfacturacion.entities.SesionUsuario;
import com.proyecto.appfacturacion.entities.Usuario;
import com.proyecto.appfacturacion.mappers.RegistrarUsuarioMapper;
import com.proyecto.appfacturacion.mappers.UsuarioMapper;
import com.proyecto.appfacturacion.repositories.SesionUsuarioRepository;
import com.proyecto.appfacturacion.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SesionUsuarioRepository sesionUsuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RegistrarUsuarioMapper registrarUsuarioMapper;



    @Override
    public UsuarioDTO createUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO updateUser(UsuarioDTO usuarioDTO, Integer id){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setApePaterno(usuarioDTO.getApePaterno());
        usuarioExistente.setApeMaterno(usuarioDTO.getApeMaterno());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setTelefono(usuarioDTO.getTelefono());

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return UsuarioMapper.toDTO(usuarioActualizado);
    }

    @Override
    public List<UsuarioDTO> getAllUsers() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTO> getUserById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return Optional.ofNullable(UsuarioMapper.toDTO(usuario));
    }

    @Override
    public void deleteUser(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void register(UsuarioDTO usuarioDTO) {

    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null ? UsuarioMapper.toDTO(usuario) : null;
    }

    public UsuarioDTO authenticate(String email, String contrasena) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            UsuarioDTO usuarioDTO = UsuarioMapper.toDTO(usuario);
            String token = jwtService.generateToken(usuario.getEmail());

            SesionUsuario sesionUsuario = new SesionUsuario();
            sesionUsuario.setIdSeUsuario(usuario);
            sesionUsuario.setTokenJwt(token);
            sesionUsuario.setFechaCreacion(new Date());
            sesionUsuario.setFechaExpiracion(new Date(System.currentTimeMillis() + jwtService.EXPIRATION_TIME));

            sesionUsuarioRepository.save(sesionUsuario);

            return usuarioDTO;
        }
        throw new RuntimeException("Credenciales inválidas");
    }

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApePaterno(usuarioDTO.getApePaterno());
        usuario.setApeMaterno(usuarioDTO.getApeMaterno());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());

        String contrasenaEncriptada = passwordEncoder.encode(usuarioDTO.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);

        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario save(RegistrarUsuarioDTO registrarUsuarioDTO) {
        Usuario usuario = RegistrarUsuarioMapper.toEntity(registrarUsuarioDTO);
        return usuarioRepository.save(usuario);
    }

}
