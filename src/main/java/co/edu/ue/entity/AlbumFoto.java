package co.edu.ue.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

/**
 * The persistent class for the album_fotos database table.
 * 
 */
@Entity
@Table(name = "album_fotos")
@NamedQuery(name = "AlbumFoto.findAll", query = "SELECT a FROM AlbumFoto a")
public class AlbumFoto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foto_id")
    private int fotoId;

    @Column(name = "nombre_foto")
    private String nombreFoto;

    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @Column(name = "descripcion")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_subida")
    private Date fechaSubida;

    @Column(name = "visible")
    private boolean visible;

    // Relación con User (muchas fotos pertenecen a un usuario)
    @ManyToOne
    @JoinColumn(name = "use_id")
    private User user;

    public AlbumFoto() {
    }

    public int getFotoId() {
        return this.fotoId;
    }

    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
    }

    public String getNombreFoto() {
        return this.nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getRutaArchivo() {
        return this.rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaSubida() {
        return this.fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}