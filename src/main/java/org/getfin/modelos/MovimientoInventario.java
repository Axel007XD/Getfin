package org.getfin.modelos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.getfin.modelos.Producto;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos_inventario")
@Data
@NoArgsConstructor

public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipoMovimiento;

    public enum TipoMovimiento { entrada, salida }

    @Column(nullable = false)
    private BigDecimal cantidad;

    private String unidad;

    @Column(nullable = false)
    private LocalDate fecha;

    private String motivo;

    public MovimientoInventario(
            Producto producto,
            TipoMovimiento tipoMovimiento,
            BigDecimal cantidad,
            String unidad,
            LocalDate fecha,
            String motivo
    ) {
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

}
