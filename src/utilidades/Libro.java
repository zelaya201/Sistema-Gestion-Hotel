/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Adonay
 */
public class Libro implements Comparable<Libro> {

    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor, String editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return isbn + " -- " + titulo + " -- " + autor + " -- " + editorial;
    }    
    
    @Override
    public int compareTo(Libro t) {
        Libro actual = this;
        return (actual.getTitulo().compareToIgnoreCase(t.getTitulo()));
    }


    
}
