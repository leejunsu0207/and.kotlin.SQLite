package com.ljs.and.sqliteopenhelpermemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ljs.and.sqliteopenhelpermemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    val helper = SqliteHelper(this, "memo", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = RecyclerAdapter()
        adapter.helper = helper

        adapter.listData.addAll(helper.selectMemo())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()){
                Log.d("MainActivity.kt", " binding.buttonSave click")
                val memo = Memo(null, binding.editMemo.text.toString(), System.currentTimeMillis())
                Log.d("MainActivity.kt", memo.toString())

                helper.insertMemo(memo)

                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }

    }




}