package pagina.web.demo.cliente;

import jakarta.validation.constraints.NotNull;

public record DTOActualizarCliente(@NotNull
                                   Long id,
                                   String nombre,
                                   String email) {
}
