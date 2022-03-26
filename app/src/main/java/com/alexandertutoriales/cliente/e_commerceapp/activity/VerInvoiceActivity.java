package com.alexandertutoriales.cliente.e_commerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.alexandertutoriales.cliente.e_commerceapp.R;
import com.github.barteksc.pdfviewer.PDFView;

public class VerInvoiceActivity extends AppCompatActivity {
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_invoice);
        byte[] pdf = getIntent().getByteArrayExtra("pdf");
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromBytes(pdf).load();
        init();
    }
    private void init(){
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v ->{
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
    }
}