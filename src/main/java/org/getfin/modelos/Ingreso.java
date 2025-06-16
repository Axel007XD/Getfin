package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.getfin.modelos.Cliente;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ingresos")
@Data
@NoArgsConstructor

public class Ingreso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngreso;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIngreso tipoIngreso;

    public enum TipoIngreso { venta_cosecha, venta_animal, venta_agroindustriado }

    private String numeroFactura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private String descripcion;

    @Column()
    private BigDecimal totalBase;

    @Column()
    private BigDecimal totalIva;

    @Column()
    private BigDecimal totalRetencion;

    @Column()
    private BigDecimal totalFinal;

    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL)
    private List<DetalleIngreso> detalles;

    public Ingreso(
            LocalDate fecha,
            TipoIngreso tipoIngreso,
            String numeroFactura,
            Cliente cliente,
            String descripcion,
            BigDecimal totalBase,
            BigDecimal totalIva,
            BigDecimal totalRetencion,
            BigDecimal totalFinal,
            List<DetalleIngreso> detalles
    ) {
        this.fecha = fecha;
        this.tipoIngreso = tipoIngreso;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.totalBase = totalBase;
        this.totalIva = totalIva;
        this.totalRetencion = totalRetencion;
        this.totalFinal = totalFinal;
        this.detalles = detalles;
    }

}
