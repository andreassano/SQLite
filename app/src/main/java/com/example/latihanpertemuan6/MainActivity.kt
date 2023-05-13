package com.example.latihanpertemuan6

import android.content.Intent
import android.content.SyncRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanpertemuan6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MahasiswaAdapter
    var addRequestCode = 1
    var updateRequestCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.root
        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)
        adapter = MahasiswaAdapter(this, arrayListOf())
        binding.rvUser.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val db = MahasiswaHelper(this)
        val mahasiswaList = db.getData()
        adapter.updateData(mahasiswaList)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_tambah ->{
                val intent = Intent(this,InsertActivity::class.java)
                startActivityForResult(intent,1)
                return true
            }else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == addRequestCode && resultCode == RESULT_OK){
            val db = MahasiswaHelper(this)
            val mahasiswaList = db.getData()
            adapter.updateData(mahasiswaList)
        }else if (requestCode == updateRequestCode && resultCode == RESULT_OK){
            val db = MahasiswaHelper(this)
            val mahasiswaList = db.getData()
            adapter.updateData(mahasiswaList)
        }
    }

    override fun onResume() {
        super.onResume()
        val db = MahasiswaHelper(this)
        val newMahasiswaList = db.getData()
        adapter.updateData(newMahasiswaList)
    }
}
