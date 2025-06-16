// Cliente.java
package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false)
    private String nombre;

    private String identificacion;
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Ingreso> ingresos;

    public Cliente(String nombre, String identificacion, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

}
