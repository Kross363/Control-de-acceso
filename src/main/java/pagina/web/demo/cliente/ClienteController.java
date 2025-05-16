package pagina.web.demo.cliente;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@SecurityRequirement(name = "bearer-key")//ESTO SIRVE PARA QUE EN ESTA URL http://localhost:8080/swagger-ui/index.html ESTE EL TOKEN GUARDADO DEL LOGEO DEL USUARIO
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DTORegistroCliente datos) {
        repository.save(new Cliente(datos));
    }

    @GetMapping
    public Page<DTOListadoCliente> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DTOListadoCliente::new);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DTOActualizarCliente datos) {
        var cliente = repository.getReferenceById(datos.id());
        cliente.actualizarInformaciones(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.eliminar();
    }
}
