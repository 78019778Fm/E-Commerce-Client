package com.alexandertutoriales.cliente.e_commerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandertutoriales.cliente.e_commerceapp.R;
import com.alexandertutoriales.cliente.e_commerceapp.api.ConfigApi;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DetallePedido;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class DetalleMisComprasAdapter extends RecyclerView.Adapter<DetalleMisComprasAdapter.ViewHolder> {
    private final List<DetallePedido> detalles;

    public DetalleMisComprasAdapter(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detalle_mis_compras, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.setItem(this.detalles.get(position));
    }

    @Override
    public int getItemCount() {
        return detalles.size();
    }
    public void updateItems(List<DetallePedido> detalles){
        this.detalles.clear();
        this.detalles.addAll(detalles);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView txtValueCodDetailPurchases, txtValuePlatillo, txtValueQuantity, txtValuePrecioPlatillo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgProduct = itemView.findViewById(R.id.imgProduct);
            this.txtValueCodDetailPurchases = itemView.findViewById(R.id.txtValueCodDetailPurchases);
            this.txtValuePlatillo = itemView.findViewById(R.id.txtValuePlatillo);
            this.txtValueQuantity = itemView.findViewById(R.id.txtValueQuantity);
            this.txtValuePrecioPlatillo = itemView.findViewById(R.id.txtValuePrecioPlatillo);
        }

        public void setItem(final DetallePedido detalle) {
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + detalle.getPlatillo().getFoto().getFileName();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.image_not_found)
                    .into(imgProduct);
            txtValueCodDetailPurchases.setText("C000" + Integer.toString(detalle.getPedido().getId()));
            txtValuePlatillo.setText(detalle.getPlatillo().getNombre());
            txtValueQuantity.setText(Integer.toString(detalle.getCantidad()));
            txtValuePrecioPlatillo.setText(String.format(Locale.ENGLISH, "S/%.2f", detalle.getPrecio()));
        }
    }
}
