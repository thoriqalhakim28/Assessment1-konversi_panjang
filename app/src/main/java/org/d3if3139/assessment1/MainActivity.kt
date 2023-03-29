package org.d3if3139.assessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.d3if3139.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var konversiDari : AutoCompleteTextView
    lateinit var konversiKe : AutoCompleteTextView
    lateinit var inputPanjang : com.google.android.material.textfield.TextInputEditText
    lateinit var btnKonversi : Button
    lateinit var btnReset : Button
    lateinit var hasilKonversi : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        konversiDari = binding.dari
        konversiKe = binding.ke
        btnKonversi = binding.btnKonveri
        btnReset = binding.btnReset
        inputPanjang = binding.inputPanjang
        hasilKonversi = binding.hasil

        val satuan_panjang = resources.getStringArray(R.array.satuan_panjang)
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

        val nilaiPanjang = nilai.toFloat()
        var hasil = 0f

        when(dari+ke) {

            // km -> m
            resources.getStringArray(R.array.satuan_panjang)[0] + resources.getStringArray(R.array.satuan_panjang)[1] -> {
                hasil = nilaiPanjang * 1000f
            }
            // km -> cm
            resources.getStringArray(R.array.satuan_panjang)[0] + resources.getStringArray(R.array.satuan_panjang)[2] -> {
                hasil = nilaiPanjang * 100000f
            }
            // km -> mm
            resources.getStringArray(R.array.satuan_panjang)[0] + resources.getStringArray(R.array.satuan_panjang)[3] -> {
                hasil = nilaiPanjang * 1000000f
            }
            // m -> km
            resources.getStringArray(R.array.satuan_panjang)[1] + resources.getStringArray(R.array.satuan_panjang)[0] -> {
                hasil = nilaiPanjang / 1000f
            }
            // m -> cm
            resources.getStringArray(R.array.satuan_panjang)[1] + resources.getStringArray(R.array.satuan_panjang)[2] -> {
                hasil = nilaiPanjang * 100f
            }
            // m -> mm
            resources.getStringArray(R.array.satuan_panjang)[1] + resources.getStringArray(R.array.satuan_panjang)[3] -> {
                hasil = nilaiPanjang * 1000f
            }
            // cm -> km
            resources.getStringArray(R.array.satuan_panjang)[2] + resources.getStringArray(R.array.satuan_panjang)[0] -> {
                hasil = nilaiPanjang / 100000f
            }
            // cm -> m
            resources.getStringArray(R.array.satuan_panjang)[2] + resources.getStringArray(R.array.satuan_panjang)[1] -> {
                hasil = nilaiPanjang / 100f
            }
            // cm -> mm
            resources.getStringArray(R.array.satuan_panjang)[2] + resources.getStringArray(R.array.satuan_panjang)[3] -> {
                hasil = nilaiPanjang * 10f
            }
            // mm -> km
            resources.getStringArray(R.array.satuan_panjang)[3] + resources.getStringArray(R.array.satuan_panjang)[0] -> {
                hasil = nilaiPanjang / 1000000f
            }
            // mm -> m
            resources.getStringArray(R.array.satuan_panjang)[3] + resources.getStringArray(R.array.satuan_panjang)[1] -> {
                hasil = nilaiPanjang / 1000f
            }
            // mm -> cm
            resources.getStringArray(R.array.satuan_panjang)[3] + resources.getStringArray(R.array.satuan_panjang)[2] -> {
                hasil = nilaiPanjang / 10f
            }
        }

        hasilKonversi.text = hasil.toString()
    }

}