package com.alexandertutoriales.cliente.e_commerceapp.activity.ui.inicio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandertutoriales.cliente.e_commerceapp.R;
import com.alexandertutoriales.cliente.e_commerceapp.adapter.CategoriaAdapter;
import com.alexandertutoriales.cliente.e_commerceapp.adapter.PlatillosRecomendadosAdapter;
import com.alexandertutoriales.cliente.e_commerceapp.adapter.SliderAdapter;
import com.alexandertutoriales.cliente.e_commerceapp.communication.Communication;
import com.alexandertutoriales.cliente.e_commerceapp.communication.MostrarBadgeCommunication;
import com.alexandertutoriales.cliente.e_commerceapp.entity.SliderItem;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DetallePedido;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Platillo;
import com.alexandertutoriales.cliente.e_commerceapp.utils.Carrito;
import com.alexandertutoriales.cliente.e_commerceapp.viewmodel.CategoriaViewModel;
import com.alexandertutoriales.cliente.e_commerceapp.viewmodel.PlatilloViewModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class InicioFragment extends Fragment implements Communication, MostrarBadgeCommunication {
    private CategoriaViewModel categoriaViewModel;
    private PlatilloViewModel platilloViewModel;
    private RecyclerView rcvPlatillosRecomendados;
    private PlatillosRecomendadosAdapter adapter;
    private List<Platillo> platillos = new ArrayList<>();
    private GridView gvCategorias;
    private CategoriaAdapter categoriaAdapter;
    private SliderView svCarrusel;
    private SliderAdapter sliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initAdapter();
        loadData();
    }

    private void init(View v){
        svCarrusel = v.findViewById(R.id.svCarrusel);
        ViewModelProvider vmp = new ViewModelProvider(this);
        //Categorías
        categoriaViewModel = vmp.get(CategoriaViewModel.class);
        gvCategorias = v.findViewById(R.id.gvCategorias);
        //Platillos
        rcvPlatillosRecomendados = v.findViewById(R.id.rcvPlatillosRecomendados);
        rcvPlatillosRecomendados.setLayoutManager(new GridLayoutManager(getContext(), 1));
        platilloViewModel = vmp.get(PlatilloViewModel.class);

    }
    private void initAdapter() {
        //Carrusel de Imágenes
        sliderAdapter = new SliderAdapter(getContext());
        svCarrusel.setSliderAdapter(sliderAdapter);
        svCarrusel.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svCarrusel.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svCarrusel.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svCarrusel.setIndicatorSelectedColor(Color.WHITE);
        svCarrusel.setIndicatorUnselectedColor(Color.GRAY);
        svCarrusel.setScrollTimeInSec(4); //set scroll delay in seconds :
        svCarrusel.startAutoCycle();
        //Categorías
        categoriaAdapter = new CategoriaAdapter(getContext(), R.layout.item_categorias, new ArrayList<>());
        gvCategorias.setAdapter(categoriaAdapter);
        //Platillos
        adapter = new PlatillosRecomendadosAdapter(platillos, this, this);
        rcvPlatillosRecomendados.setAdapter(adapter);
    }
    private void loadData() {

        List<SliderItem> lista = new ArrayList<>();
        lista.add(new SliderItem(R.drawable.platillos_tipicos, "Los Mejores Platillos"));
        lista.add(new SliderItem(R.drawable.postres_ricos, "Los Mejores Postres"));
        lista.add(new SliderItem(R.drawable.postres_muysabrosos, "Los Mejores Postres"));
        lista.add(new SliderItem(R.drawable.peru_postres, "Los Mejores Postres"));
        sliderAdapter.updateItem(lista);
        categoriaViewModel.listarCategoriasActivas().observe(getViewLifecycleOwner(), response -> {
            if(response.getRpta() == 1){
                categoriaAdapter.clear();
                categoriaAdapter.addAll(response.getBody());
                categoriaAdapter.notifyDataSetChanged();
            }else{
                System.out.println("Error al obtener las categorías activas");
            }
        });
        platilloViewModel.listarPlatillosRecomendados().observe(getViewLifecycleOwner(), response -> {
            adapter.updateItems(response.getBody());
        });

    }


    @Override
    public void showDetails(Intent i) {
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    @Override
    public void add(DetallePedido dp) {
        successMessage(Carrito.agregarPlatillos(dp));
        BadgeDrawable badgeDrawable = BadgeDrawable.create(this.getContext());
        badgeDrawable.setNumber(Carrito.getDetallePedidos().size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable, getActivity().findViewById(R.id.toolbar), R.id.bolsaCompras);
    }
    public void successMessage(String message) {
        new SweetAlertDialog(this.getContext(),
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).show();
    }
}