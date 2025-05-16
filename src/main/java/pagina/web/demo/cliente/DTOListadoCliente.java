package pagina.web.demo.cliente;

public record DTOListadoCliente(Long id, String nombre, String email, String dni) {
    public DTOListadoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNombre(), cliente.getEmail(), cliente.getDni());
    }
}
