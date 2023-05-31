/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCoffeco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nico_
 */
@Entity
@Table(name = "CESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cesta.findAll", query = "SELECT c FROM Cesta c"),
    @NamedQuery(name = "Cesta.findByIdcesta", query = "SELECT c FROM Cesta c WHERE c.idcesta = :idcesta"),
    @NamedQuery(name = "Cesta.findByCantidad", query = "SELECT c FROM Cesta c WHERE c.cantidad = :cantidad")})
public class Cesta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCESTA")
    private BigDecimal idcesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @ManyToOne(optional = false)
    private Productos idproducto;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuarios idusuario;

    public Cesta() {
    }

    public Cesta(BigDecimal idcesta) {
        this.idcesta = idcesta;
    }

    public Cesta(BigDecimal idcesta, BigInteger cantidad) {
        this.idcesta = idcesta;
        this.cantidad = cantidad;
    }

    public BigDecimal getIdcesta() {
        return idcesta;
    }

    public void setIdcesta(BigDecimal idcesta) {
        this.idcesta = idcesta;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Productos idproducto) {
        this.idproducto = idproducto;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcesta != null ? idcesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cesta)) {
            return false;
        }
        Cesta other = (Cesta) object;
        if ((this.idcesta == null && other.idcesta != null) || (this.idcesta != null && !this.idcesta.equals(other.idcesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBCoffeco.Cesta[ idcesta=" + idcesta + " ]";
    }
    
}
