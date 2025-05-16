package pagina.web.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pagina.web.demo.usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Control de acceso")
                    .withSubject(usuario.getLogin())//USERNAME
                    .withClaim("id",usuario.getId())//ID
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }
    //VERIFICACION DEL TOKEN
    public String getSubject(String token) {
        if (token==null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);//VALIDANDO FIRMA DEL TOKEN
            verifier = JWT.require(algorithm)
                    .withIssuer("Control de acceso")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println(exception.toString());
        }
        if (verifier.getSubject()==null){
            throw new RuntimeException("Verificador invalido");
        }
        return verifier.getSubject();//EN ESTE CASO NOS DEVUELVE EL NOMBRE DEL USUARIO
    }
    //ESTE METODO GENERA LA FECHA DE EXPIRACION DEL TOKEN
    //EN .plusHours(2) ESTAMOS INDICANDO QUE EXPIRA EN DOS HORAS
    //(ZoneOffset.of("-05:00") ES ZONA HORARIA DE SUDAMERICA
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
