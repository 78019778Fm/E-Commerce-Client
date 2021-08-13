package com.alexandertutoriales.cliente.e_commerceapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alexandertutoriales.cliente.e_commerceapp.api.ConfigApi;
import com.alexandertutoriales.cliente.e_commerceapp.api.PedidoApi;
import com.alexandertutoriales.cliente.e_commerceapp.entity.GenericResponse;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Pedido;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.dto.GenerarPedidoDTO;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.dto.PedidoConDetallesDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoRepository {
    private final PedidoApi api;
    private static PedidoRepository repository;

    public PedidoRepository() {
        this.api = ConfigApi.getPedidoApi();
    }

    public static PedidoRepository getInstance() {
        if (repository == null) {
            repository = new PedidoRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<List<PedidoConDetallesDTO>>> listarPedidosPorCliente(int idCli){
        final MutableLiveData<GenericResponse<List<PedidoConDetallesDTO>>> mld = new MutableLiveData<>();
        this.api.listarPedidosPorCliente(idCli).enqueue(new Callback<GenericResponse<List<PedidoConDetallesDTO>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<PedidoConDetallesDTO>>> call, Response<GenericResponse<List<PedidoConDetallesDTO>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<PedidoConDetallesDTO>>> call, Throwable t) {
                mld.setValue(new GenericResponse());
                System.out.println("Error al obtener los pedidos: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }

    //GUARDAR PEDIDO CON DETALLES
    public LiveData<GenericResponse<GenerarPedidoDTO>> save(GenerarPedidoDTO dto) {
        MutableLiveData<GenericResponse<GenerarPedidoDTO>> data = new MutableLiveData<>();
        api.guardarPedido(dto).enqueue(new Callback<GenericResponse<GenerarPedidoDTO>>() {
            @Override
            public void onResponse(Call<GenericResponse<GenerarPedidoDTO>> call, Response<GenericResponse<GenerarPedidoDTO>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<GenerarPedidoDTO>> call, Throwable t) {
                data.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return data;
    }
    //ANULAR PEDIDO
    public LiveData<GenericResponse<Pedido>> anularPedido(int id){
        MutableLiveData<GenericResponse<Pedido>> mld = new MutableLiveData<>();
        this.api.anularPedido(id).enqueue(new Callback<GenericResponse<Pedido>>() {
            @Override
            public void onResponse(Call<GenericResponse<Pedido>> call, Response<GenericResponse<Pedido>> response) {
                if(response.isSuccessful()){
                    mld.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<Pedido>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
