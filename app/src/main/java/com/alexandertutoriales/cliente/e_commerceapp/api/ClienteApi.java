package com.alexandertutoriales.cliente.e_commerceapp.api;

import com.alexandertutoriales.cliente.e_commerceapp.entity.GenericResponse;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Cliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteApi {

    String base = "api/cliente";
    @POST(base)
    Call<GenericResponse<Cliente>> guardarCliente(@Body Cliente c);
}
