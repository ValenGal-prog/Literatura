package com.alura.ChallengeLiteratura.modelos;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "autores")

public class AutorDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    private List<LibroDB> libros;

    public AutorDB(){
    }

    public AutorDB(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombreAutor();
        this.anioDeNacimiento = datosAutor.anioDeNacimiento();
        this.anioDeFallecimiento = datosAutor.anioDeFallecimiento();
    }

    @Override
    public String toString() {

        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");

        for(int i = 0; i < libros.size() ; i++) {
            librosStr.append(libros.get(i).getTitulo());
            if (i < libros.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("********** Autor **********%nNombre:" +
                " %s%n%s%nFecha de Nacimiento: %s%nFecha de Fallecimiento:" +
                " %s%n***************************%n",nombre,librosStr.toString(),anioDeNacimiento,anioDeFallecimiento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public void setAnioDeNacimiento(Integer anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }

    public Integer getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }

    public void setAnioDeFallecimiento(Integer anioDeFallecimiento) {
        this.anioDeFallecimiento = anioDeFallecimiento;
    }

    public List<LibroDB> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroDB> libros) {
        this.libros = libros;
    }
}
