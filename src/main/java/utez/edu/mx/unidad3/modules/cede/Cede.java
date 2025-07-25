package utez.edu.mx.unidad3.modules.cede;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import utez.edu.mx.unidad3.modules.warehouse.Warehouse;

import java.util.List;

@Entity
@Table(name = "cede")
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @Column(name = "clave", nullable = false)
//    private String clave;
//
//    @Column(name = "state", nullable = false)
//    private String state;
//
//    @Column(name = "city", nullable = false)
//    private String city;

    @Pattern(
            regexp = "^C\\d+-\\d{8}-\\d{4}$",
            message = "La clave debe seguir el formato C[id]-[ddMMyyyy]-[4 dígitos]"
    )
    @NotNull(message = "Ingresa la clave de la cede")
    @NotBlank(message = "La clave no puede estar vacía")
    @Column(name = "clave", nullable = false, unique = true)
    private String clave;

    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ][\\sA-Za-zÁÉÍÓÚáéíóúÑñ]{2,}$",
            message = "Solo se aceptan letras y espacios, mínimo 3 caracteres"
    )
    @NotNull(message = "Ingresa el estado")
    @NotBlank(message = "El estado no puede estar vacío")
    @Column(name = "state", nullable = false)
    private String state;

    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ][\\sA-Za-zÁÉÍÓÚáéíóúÑñ]{2,}$",
            message = "Solo se aceptan letras y espacios, mínimo 3 caracteres"
    )
    @NotNull(message = "Ingresa la ciudad")
    @NotBlank(message = "La ciudad no puede estar vacía")
    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "cede")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Cede(Long id, String clave, String state, String city, List<Warehouse> warehouses) {
        this.id = id;
        this.clave = clave;
        this.state = state;
        this.city = city;
        this.warehouses = warehouses;
    }

    public Cede() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
