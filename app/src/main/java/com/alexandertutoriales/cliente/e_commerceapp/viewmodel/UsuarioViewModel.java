package com.alexandertutoriales.cliente.e_commerceapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alexandertutoriales.cliente.e_commerceapp.entity.GenericResponse;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Usuario;
import com.alexandertutoriales.cliente.e_commerceapp.repository.UsuarioRepository;

import org.jetbrains.annotations.NotNull;

public class UsuarioViewModel extends AndroidViewModel {
 private final UsuarioRepository repository;
    public UsuarioViewModel(@NonNull @NotNull Application application) {
        super(application);
        this.repository = UsuarioRepository.getInstance();
    }
    public LiveData<GenericResponse<Usuario>> login(String email, String pass){
        return this.repository.login(email, pass);
    }

    public LiveData<GenericResponse<Usuario>> save(Usuario u){
        return this.repository.save(u);
    }
}
