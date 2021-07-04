package com.alexandertutoriales.cliente.e_commerceapp.entity.service;


import okhttp3.MultipartBody;

public class DocumentoAlmacenado {

    private long id;
    private String nombre;
    private String fileName;
    private String extension;
    private String estado;
    private boolean eliminado;
    private String urlFile;
    private MultipartBody.Part file;

    public DocumentoAlmacenado() {
        id = 0;
        nombre = "";
        fileName = "";
        extension = "";
        estado = "A";
        eliminado = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getCompleteFileName() {
        return fileName + extension;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }
}
