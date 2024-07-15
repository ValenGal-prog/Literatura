package com.alura.ChallengeLiteratura.modelos;

public class LibroDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private AutorDB autor;
    @Enumerated(EnumType.STRING)
    private DatosIdioma idioma;
    private Integer numeroDeDescargas;

    public LibroDB() {
    }

    public LibroDB(DatosLibros datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*********** Libro ***********\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor != null ? autor.getNombre() : "Desconocido").append("\n");
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("Número de descargas: ").append(numeroDeDescargas).append("\n");
        sb.append("****************************\n");
        return sb.toString();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public DatosIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(DatosIdioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public AutorDB getAutor() {
        return autor;
    }

    public void setAutor(AutorDB autor) {
        this.autor = autor;
    }
}
