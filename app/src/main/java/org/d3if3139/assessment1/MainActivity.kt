package org.d3if3139.assessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import org.d3if3139.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var konversiDari : AutoCompleteTextView
    lateinit var konversiKe : AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        konversiDari = binding.dari
        konversiKe = binding.ke

        val satuan_panjang = resources.getStringArray(R.array.list_panjang)
        val adapter = ArrayAdapter(this, R.layout.list_panjang, satuan_panjang)

        konversiDari.setAdapter(adapter)
        konversiKe.setAdapter(adapter)
    }
}