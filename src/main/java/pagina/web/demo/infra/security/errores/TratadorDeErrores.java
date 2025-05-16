package pagina.web.demo.infra.security.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TratadorDeErrores {
    //COLOCAR ESTA ANOTACION Y COMO PARAMETRO EL TIPO DE ERROR QUE TENEMOS
    //EN ESTE CASO EL ERROR ES EntityNotFoundException QUE ES QUE NO ENCONTRO UN ID
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataError404(){
        return ResponseEntity.notFound().build();
    }
    //ESTOS TIPOS DE ERRORES SE ENCUENTRAN EN LOS LOGS CUANDO EJECUTAMOS LA APLICACION
    //EN ESTE CASO EL ERROR MethodArgumentNotValidException SUCEDE CUANDO REGISTRAMOS UN MEDICO Y FALTA ALGUN DATO (EMAIL,DNI,NOMBRE,ETC)
    }
