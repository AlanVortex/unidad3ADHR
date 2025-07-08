package utez.edu.mx.unidad3.modules.auth;

import utez.edu.mx.unidad3.modules.auth.dto.LoginRequestDTO;
import utez.edu.mx.unidad3.modules.user.BeanUser;
import utez.edu.mx.unidad3.modules.user.UserRepository;
import utez.edu.mx.unidad3.security.jwt.JWTUtils;
import utez.edu.mx.unidad3.security.jwt.UDService;
import utez.edu.mx.unidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.PasswordEncoder;

import java.sql.SQLException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UDService udService;

    @Autowired
    private JWTUtils jwtUtils;


    @Transactional(readOnly = true)
    public APIResponse doLogin(LoginRequestDTO payload) {
        BeanUser found = userRepository.findByUsername(payload.getUsername()).orElse(null);
        try {
            if (found == null) {
                return new APIResponse(
                        "Usuario no encontrado",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }
            if (!PasswordEncoder.verifyPassword(payload.getPassword(), found.getPassword())) {
                return new APIResponse(
                        "Usuario o contraseña no conciden",
                        true,
                        HttpStatus.BAD_REQUEST

                );
            }
            UserDetails ud = udService.loadUserByUsername(found.getUsername());
            String token = jwtUtils.generateToken(ud);
            return new APIResponse("Operacion exitosa", token, false,  HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new APIResponse(
                    "Error al inicar session",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR

            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse register(BeanUser payload) {
        try {
            BeanUser found = userRepository.findByUsername(payload.getUsername()).orElse(null);
            if (found != null) {
                return new APIResponse(
                        "Usuario ya existe",
                        true,
                        HttpStatus.INTERNAL_SERVER_ERROR

                );
            }
            payload.setPassword(PasswordEncoder.encodePassword(payload.getPassword()));
            userRepository.save(payload);
            return new APIResponse("Operacion exitosa",  false, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new APIResponse(
                    "Error al registar usuario",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR

            );
        }
    }
}