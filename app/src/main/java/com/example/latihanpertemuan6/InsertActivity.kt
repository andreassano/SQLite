package com.example.latihanpertemuan6

import android.app.Activity
import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.latihanpertemuan6.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val nama = binding.inputNama.text.toString()
            val nim = binding.inputNim.text.toString()
            val password = binding.inputPassword.text.toString()
            val db = MahasiswaHelper(this)
            val mahasiswa = Mahasiswa(email, nama, nim, password)

            if (email.isNotEmpty() && nama.isNotEmpty() && nim.isNotEmpty() && password.isNotEmpty()){
                db.insertData(mahasiswa)
            Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            finish()
        }else{
            Toast.makeText(this, "Data Tidak Boleh Kosong!!!", Toast.LENGTH_SHORT).show()
            }
    }
}

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }else -> super.onContextItemSelected(item)
        }
    }
}