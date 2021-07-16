package com.alexandertutoriales.cliente.e_commerceapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alexandertutoriales.cliente.e_commerceapp.entity.GenericResponse;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DocumentoAlmacenado;
import com.alexandertutoriales.cliente.e_commerceapp.repository.DocumentoAlmacenadoRepository;

import org.jetbrains.annotations.NotNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DocumentoAlmacenadoViewModel extends AndroidViewModel {
    private final DocumentoAlmacenadoRepository repository;

    public DocumentoAlmacenadoViewModel(@NonNull Application application) {
        super(application);
        this.repository = DocumentoAlmacenadoRepository.getInstance();
    }
    public LiveData<GenericResponse<DocumentoAlmacenado>> save(MultipartBody.Part part, RequestBody requestBody){
        return this.repository.savePhoto(part, requestBody);
    }
}
