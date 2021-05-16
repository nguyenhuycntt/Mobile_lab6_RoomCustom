package com.example.roomcustom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    Button btnAdd;
    Button btnCancel;
    EditText editAddress;
    public static List<DiaChi> list = new ArrayList<>();
    AddressAdapter.OnDiaChiListener onDiaChiListener;
    public static AddressAdapter adapter;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
//        list= ConnectDB.getInstance(MainActivity.this).addressDAO().getAllAddresses();
        list = ConnectDB.getInstance(MainActivity.this).diaChiDAO().getAllDiaChi();
        adapter = new AddressAdapter(MainActivity.this,list,onDiaChiListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        btnAdd = findViewById(R.id.btnCancel);
        editAddress = findViewById(R.id.editTextTextMultiLine);
        btnAdd.setOnClickListener(v -> {
            String city = editAddress.getText().toString();
            ConnectDB.getInstance(MainActivity.this).diaChiDAO().addDiaChi(new DiaChi(city));
            list= ConnectDB.getInstance(MainActivity.this).diaChiDAO().getAllDiaChi();
            adapter = new AddressAdapter(MainActivity.this,list,onDiaChiListener);
            recyclerView.setAdapter(adapter);
            editAddress.setText("");
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
        });
        adapter.setOnDiaChiListener(new AddressAdapter.OnDiaChiListener() {
        });
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {
            editAddress.setText("");
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
        });
    }
}