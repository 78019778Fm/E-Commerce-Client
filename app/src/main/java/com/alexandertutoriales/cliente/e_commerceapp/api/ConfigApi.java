package com.alexandertutoriales.cliente.e_commerceapp.api;

import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DocumentoAlmacenado;
import com.alexandertutoriales.cliente.e_commerceapp.utils.DateSerializer;
import com.alexandertutoriales.cliente.e_commerceapp.utils.TimeSerializer;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    public static final String baseUrlE = "http://10.0.2.2:9090";
    public static final String ipAlexander = "http://192.168.101.8:9090";
    private static Retrofit retrofit;
    private static String token = "";

    private static UsuarioApi usuarioApi;
    private static ClienteApi clienteApi;
    private static DocumentoAlmacenadoApi documentoAlmacenadoApi;
    private static CategoriaApi categoriaApi;
    private static PlatilloApi platilloApi;
    private static PedidoApi pedidoApi;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlE)//Si quieren ejecutar la app desde su móvil, cambiar aquí con la ip de su ordenador
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    public static OkHttpClient getClient() {
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(loggin)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(stetho);
        return builder.build();
    }

    public static void setToken(String value) {
        token = value;
        initClient();
    }

    public static UsuarioApi getUsuarioApi() {
        if (usuarioApi == null) {
            usuarioApi = retrofit.create(UsuarioApi.class);
        }
        return usuarioApi;
    }

    public static ClienteApi getClienteApi() {
        if (clienteApi == null) {
            clienteApi = retrofit.create(ClienteApi.class);
        }
        return clienteApi;
    }

    public static DocumentoAlmacenadoApi getDocumentoAlmacenadoApi() {
        if (documentoAlmacenadoApi == null) {
            documentoAlmacenadoApi = retrofit.create(DocumentoAlmacenadoApi.class);
        }
        return documentoAlmacenadoApi;
    }

    public static CategoriaApi getCategoriaApi() {
        if (categoriaApi == null) {
            categoriaApi = retrofit.create(CategoriaApi.class);
        }
        return categoriaApi;
    }

    public static PlatilloApi getPlatilloApi() {
        if (platilloApi == null) {
            platilloApi = retrofit.create(PlatilloApi.class);
        }
        return platilloApi;
    }
    public static PedidoApi getPedidoApi(){
        if(pedidoApi == null){
            pedidoApi = retrofit.create(PedidoApi.class);
        }
        return pedidoApi;
    }
}
