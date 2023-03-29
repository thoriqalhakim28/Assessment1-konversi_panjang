package org.d3if3139.assessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import org.d3if3139.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var konversiDari : AutoCompleteTextView
    lateinit var konversiKe : AutoCompleteTextView
    lateinit var inputPanjang : com.google.android.material.textfield.TextInputEditText
    lateinit var btnKonversi : Button
    lateinit var btnReset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        konversiDari = binding.dari
        konversiKe = binding.ke
        btnKonversi = binding.btnKonveri
        btnReset = binding.btnReset
        inputPanjang = binding.inputPanjang

        val satuan_panjang = resources.getStringArray(R.array.list_panjang)
        val adapter = ArrayAdapter(this, R.layout.list_panjang, satuan_panjang)

        konversiDari.setAdapter(adapter)
        konversiKe.setAdapter(adapter)

        btnKonversi.setOnClickListener {
            konversiPanjang(
                konversiDari.text.toString(),
                konversiKe.text.toString(),
                inputPanjang.text.toString()
            )
        }
    }

    private fun konversiPanjang(dari: String, ke: String, nilai: String) {

        // Validasi
        var jikaSama = false
        var jikaKosong = false

        // Mengecek apakah nilai inputan sudah diisi atau belum
        if(TextUtils.isEmpty(nilai)) {
            Toast.makeText(this, "Harap masukan nilai terlebih dahulu", Toast.LENGTH_LONG).show()
            return
        }

        // Mengecek apakah pilihan satuan panjang sama atau tidak
        if(dari.equals(ke, false)) {
            jikaSama = true
            Toast.makeText(this, "Pilih satuan panjang yang berbeda", Toast.LENGTH_SHORT).show()
        }
    }

}