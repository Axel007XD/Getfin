package org.getfin.modelos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "egresos")
@Data
@NoArgsConstructor

public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    private double montoTotal;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "egreso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEgreso> detalles;

    public Egreso(LocalDate fecha, double montoTotal, String descripcion, Usuario usuario, List<DetalleEgreso> detalles) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.detalles = detalles;
    }


}
