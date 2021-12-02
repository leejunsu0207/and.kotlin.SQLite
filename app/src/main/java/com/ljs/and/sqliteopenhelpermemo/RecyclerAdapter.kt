package com.ljs.and.sqliteopenhelpermemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ljs.and.sqliteopenhelpermemo.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    var helper: SqliteHelper? = null
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        var mMemo: Memo? = null

        init {
            binding.buttonDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                listData.remove(mMemo)
                notifyDataSetChanged()
            }
        }

        fun setMemo(memo: Memo){

            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            // 날짜 포맷은 SimpleDateFormat으로 설정함
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"

            this.mMemo = memo

            Log.d("Holder", "${memo.no}")
            Log.d("Holder", memo.content)
            Log.d("Holder", "${sdf.format(memo.datetime)}")
        }


    }



}

