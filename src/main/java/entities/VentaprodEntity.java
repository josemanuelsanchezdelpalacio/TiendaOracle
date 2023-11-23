package entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "VENTAPROD", schema = "ROOT")
public class VentaprodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "ID_CLIENTE")
    private long idCliente;
    @Basic
    @Column(name = "ID_PRODUCTO")
    private long idProducto;
    @Basic
    @Column(name = "UNIDADES")
    private short unidades;
    @Basic
    @Column(name = "FECHA")
    private Date fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public short getUnidades() {
        return unidades;
    }

    public void setUnidades(short unidades) {
        this.unidades = unidades;
    }

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

        if (id != that.id) return false;
        if (idCliente != that.idCliente) return false;
        if (idProducto != that.idProducto) return false;
        if (unidades != that.unidades) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (idCliente ^ (idCliente >>> 32));
        result = 31 * result + (int) (idProducto ^ (idProducto >>> 32));
        result = 31 * result + (int) unidades;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
