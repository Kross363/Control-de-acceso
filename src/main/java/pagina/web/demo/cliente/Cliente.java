package pagina.web.demo.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private String email;
        private String dni;
        private Boolean activo;

        public Cliente(DTORegistroCliente datos){
            this.activo = true;
            this.nombre = datos.nombre();
            this.email = datos.email();
            this.dni = datos.dni();
        }

    public void actualizarInformaciones(DTOActualizarCliente datos){
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.email() != null) {
            this.email = datos.email();
        }
    }
    public void eliminar() {
        this.activo = false;
    }
}
