package entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "VENTAPROD", schema = "ROOT")
public class VentaprodEntity {
    private BigInteger id;
    private String nif;
    private Short codProducto;
    private Short unidades;
    private Date fecha;

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
    @Column(name = "NIF", nullable = false, length = 10)
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Basic
    @Column(name = "COD_PRODUCTO", nullable = false, precision = 0)
    public Short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Short codProducto) {
        this.codProducto = codProducto;
    }

    @Basic
    @Column(name = "UNIDADES", nullable = false, precision = 0)
    public Short getUnidades() {
        return unidades;
    }

    public void setUnidades(Short unidades) {
        this.unidades = unidades;
    }

    @Basic
    @Column(name = "FECHA", nullable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentaprodEntity that = (VentaprodEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (codProducto != null ? !codProducto.equals(that.codProducto) : that.codProducto != null) return false;
        if (unidades != null ? !unidades.equals(that.unidades) : that.unidades != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (codProducto != null ? codProducto.hashCode() : 0);
        result = 31 * result + (unidades != null ? unidades.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
