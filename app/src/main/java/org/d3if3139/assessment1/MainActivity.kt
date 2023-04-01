package org.d3if3139.assessment1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import org.d3if3139.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var konversiDari : AutoCompleteTextView
    lateinit var konversiKe : AutoCompleteTextView
    lateinit var inputPanjang : com.google.android.material.textfield.TextInputEditText
    lateinit var btnKonversi : Button
    lateinit var btnReset : Button
    lateinit var hasilKonversi : TextView
    lateinit var switchBtn : androidx.appcompat.widget.SwitchCompat

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
        switchBtn = binding.gantiTema

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

        btnReset.setOnClickListener { resetHasil() }
        tampilkanHasil(false)

        val sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        val modeGelap = sharedPreferences.getBoolean("night", false)
        val editor = sharedPreferences.edit()

        if(modeGelap) {
            switchBtn.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        switchBtn.setOnCheckedChangeListener { gantiMode, isChecked ->
            if(!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()

            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night",true)
                editor.apply()
            }
        }

    }


    private fun tampilkanHasil(visible: Boolean) {
        // Menghide tombol reset dan hasil konversi
        if(!visible) {
            btnReset.visibility = View.INVISIBLE
            hasilKonversi.visibility = View.INVISIBLE
        }else {
            btnReset.visibility = View.VISIBLE
            hasilKonversi.visibility = View.VISIBLE
        }
    }

    private fun resetHasil() {
        inputPanjang.text?.clear()
        tampilkanHasil(false)
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
        tampilkanHasil(true)
    }
}