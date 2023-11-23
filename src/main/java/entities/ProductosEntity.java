package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTOS", schema = "ROOT")
public class ProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "COD_PRODUCTO")
    private short codProducto;
    @Basic
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic
    @Column(name = "LINEA_PRODUCTO")
    private String lineaProducto;
    @Basic
    @Column(name = "PRECIO")
    private Short precio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(short codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

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

        if (id != that.id) return false;
        if (codProducto != that.codProducto) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (lineaProducto != null ? !lineaProducto.equals(that.lineaProducto) : that.lineaProducto != null)
            return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) codProducto;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (lineaProducto != null ? lineaProducto.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        return result;
    }
}
