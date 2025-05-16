package pagina.web.demo.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTORegistroCliente (@NotBlank
                                  String nombre,
                                  @NotBlank
                                  @Email
                                  String email,@NotBlank
                                  @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}")
                                  //VALIDA EL PATRON DEL DNI, EN ESTE CASO LO QUE ESTA EN COMILLAS DICE QUE EL DNI DEBE TENER 2 DIGITOS, SEGUIDO DE UN PUNTO, 3 DIGITOS, UN PUNTO Y OTROS 3 DIGITOS
                                  //POR EJEMPLO: 19.436.777
                                  String dni){
}
