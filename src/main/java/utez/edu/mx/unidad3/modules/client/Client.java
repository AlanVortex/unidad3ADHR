package utez.edu.mx.unidad3.modules.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import utez.edu.mx.unidad3.modules.warehouse.Warehouse;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^[a-zA-záéíóúÁÉÍÓÚñÑ][\\sa-zA-záéíóúÁÉÍÓÚñÑ]{3,}$", message = "Solo se aceptan letras")
    @NotNull(message = "Ingresa los datos")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 10, max = 10, message = "Debe ser al menos un num. telefonico de 10 digitos")
    @Pattern(regexp = "^\\d+$", message = "Solo se aceptan numeros")
    @NotNull(message = "Ingresa los datos")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Email
    @Pattern(regexp = "^[a-z0-9._-]+@[a-z0-9]{2,}(\\.[a-z0-9]{2,}){1,2}$", message = "Coloca un correo valido")
    @NotNull(message = "Ingresa los datos de correo")
    @NotBlank(message = "No puedes dejar este campo vacio")
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Client() {

    }

    public Client(Long id, String name, String phone, String email, List<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
