package entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "PRODUCTOS", schema = "ROOT")
public class ProductosEntity {
    private BigInteger id;
    private Short codProducto;
    private String descripcion;
    private String lineaProducto;
    private Short precio;
    private VentaprodEntity ventaprodByCodProducto;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COD_PRODUCTO", nullable = false, precision = 0, insertable = false, updatable = false)
    public Short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Short codProducto) {
        this.codProducto = codProducto;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 20)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "LINEA_PRODUCTO", nullable = true, length = 10)
    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

    @Basic
    @Column(name = "PRECIO", nullable = true, precision = 0)
    public Short getPrecio() {
        return precio;
    }

    public void setPrecio(Short precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductosEntity that = (ProductosEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (codProducto != null ? !codProducto.equals(that.codProducto) : that.codProducto != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (lineaProducto != null ? !lineaProducto.equals(that.lineaProducto) : that.lineaProducto != null)
            return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (codProducto != null ? codProducto.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (lineaProducto != null ? lineaProducto.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO", nullable = false)
    public VentaprodEntity getVentaprodByCodProducto() {
        return ventaprodByCodProducto;
    }

    public void setVentaprodByCodProducto(VentaprodEntity ventaprodByCodProducto) {
        this.ventaprodByCodProducto = ventaprodByCodProducto;
    }
}
