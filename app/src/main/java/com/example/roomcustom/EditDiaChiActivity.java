package com.example.roomcustom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditDiaChiActivity extends AppCompatActivity {
    TextView id;
    Button btnEdit;
    EditText editCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dia_chi);
        id = findViewById(R.id.idDiaChi);
        btnEdit = findViewById(R.id.btnEditAddress);
        editCity = findViewById(R.id.tvEditDiaChi);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        DiaChi diaChi = (DiaChi) bundle.getSerializable("diachi");
        id.setText(new StringBuilder("ID : ").append(diaChi.getId()));
        btnEdit.setOnClickListener(v -> {
            String city  = editCity.getText().toString();
            DiaChi diaChiNew = new DiaChi(diaChi.getId(),city);
            ConnectDB.getInstance().diaChiDAO().updateDiachi(diaChiNew);
            Intent intent1 = new Intent(EditDiaChiActivity.this,MainActivity.class);
            startActivity(intent1);
            finish();
        });
    }
}