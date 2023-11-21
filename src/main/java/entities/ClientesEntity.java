package entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "CLIENTES", schema = "ROOT")
public class ClientesEntity {
    private BigInteger id;
    private String nif;
    private String nombre;
    private String localidad;
    private VentaprodEntity ventaprodByNif;

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
    @Column(name = "NIF", nullable = false, length = 10, insertable = false, updatable = false)
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 14)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "LOCALIDAD", nullable = true, length = 14)
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientesEntity that = (ClientesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (localidad != null ? !localidad.equals(that.localidad) : that.localidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (localidad != null ? localidad.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NIF", referencedColumnName = "NIF", nullable = false)
    public VentaprodEntity getVentaprodByNif() {
        return ventaprodByNif;
    }

    public void setVentaprodByNif(VentaprodEntity ventaprodByNif) {
        this.ventaprodByNif = ventaprodByNif;
    }
}
