package pagina.web.demo.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT c.activo
            FROM Cliente c
            WHERE
            c.id=:idCliente
            """)
    boolean findActivoById(Long idCliente);
}
