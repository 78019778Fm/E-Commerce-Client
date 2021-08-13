package com.alexandertutoriales.cliente.e_commerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandertutoriales.cliente.e_commerceapp.R;
import com.alexandertutoriales.cliente.e_commerceapp.api.ConfigApi;
import com.alexandertutoriales.cliente.e_commerceapp.communication.MostrarBadgeCommunication;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DetallePedido;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Platillo;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class PlatillosPorCategoriaAdapter extends RecyclerView.Adapter<PlatillosPorCategoriaAdapter.ViewHolder> {
    private List<Platillo> listadoPlatilloPorCategoria;
    private final MostrarBadgeCommunication mostrarBadgeCommunication;

    public PlatillosPorCategoriaAdapter(List<Platillo> listadoPlatilloPorCategoria, MostrarBadgeCommunication mostrarBadgeCommunication) {
        this.listadoPlatilloPorCategoria = listadoPlatilloPorCategoria;
        this.mostrarBadgeCommunication = mostrarBadgeCommunication;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_platillos_por_categoria, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.listadoPlatilloPorCategoria.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listadoPlatilloPorCategoria.size();
    }
    public void updateItems(List<Platillo> platillosByCategoria){
        this.listadoPlatilloPorCategoria.clear();
        this.listadoPlatilloPorCategoria.addAll(platillosByCategoria);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPlatilloC;
        private final TextView namePlatilloC, txtPricePlatilloC;
        private final Button btnOrdenarPC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPlatilloC = itemView.findViewById(R.id.imgPlatilloC);
            this.namePlatilloC = itemView.findViewById(R.id.namePlatilloC);
            this.txtPricePlatilloC = itemView.findViewById(R.id.txtPricePlatilloC);
            this.btnOrdenarPC = itemView.findViewById(R.id.btnOrdenarPC);
        }

        public void setItem(final Platillo p) {
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + p.getFoto().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //No lo almacena el la caché ni en el disco
                    .error(R.drawable.image_not_found)
                    .into(imgPlatilloC);
            namePlatilloC.setText(p.getNombre());
            txtPricePlatilloC.setText(String.format(Locale.ENGLISH, "S/%.2f", p.getPrecio()));
            btnOrdenarPC.setOnClickListener(v -> {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setPlatillo(p);
                detallePedido.setCantidad(1);
                detallePedido.setPrecio(p.getPrecio());
                mostrarBadgeCommunication.add(detallePedido);
            });
        }
    }
}
