package com.llaerto.keepmymoney.usecases.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.llaerto.keepmymoney.R
import com.llaerto.keepmymoney.databinding.ItemRecordBinding
import com.llaerto.keepmymoney.model.RecordRow
import com.llaerto.keepmymoney.utils.DiffCallbackRecords
import com.llaerto.keepmymoney.utils.setGone
import com.llaerto.keepmymoney.utils.setVisible


class HomeAdapter(
    private val clickListener: ((RecordRow) -> Unit)? = null
) :
    RecyclerView.Adapter<HomeAdapter.RecordViewHolder>() {

    private val list: MutableList<RecordRow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(list: MutableList<RecordRow>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): RecordRow {
        return list[position]
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateList(newList: List<RecordRow>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallbackRecords(newList, list))
        diffResult.dispatchUpdatesTo(this)
    }

    fun restoreItem(item: RecordRow, position: Int) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    class RecordViewHolder(var binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordRow, clickListener: ((RecordRow) -> Unit)?) {
            with(record) {
                    clickListener?.let {
                        itemView.setOnClickListener {
                            it(record)
                        }
                    }
                binding.count.text = count
                comment?.run {
                    binding.comment.setVisible()
                    binding.comment.text = this
                } ?: binding.comment.setGone()
                setColor(category.color)
                binding.count.setTextColor(binding.count.context.getColor(if (isIncome) R.color.colorGreen else R.color.colorRed))
                binding.date.text = date
            }
        }

        private fun setColor(color: String) {
            val layerList = binding.container.background as LayerDrawable
            val item = layerList.findDrawableByLayerId(R.id.marker) as GradientDrawable
            item.setColor(Color.parseColor(color))
        }

    }
}