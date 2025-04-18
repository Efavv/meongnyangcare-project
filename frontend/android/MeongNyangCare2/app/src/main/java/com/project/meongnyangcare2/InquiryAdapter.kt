package com.project.meongnyangcare2

import Inquiry
import Pet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InquiryAdapter(
    private val inquiryList: List<Inquiry>,
    private val onItemClick: (Inquiry) -> Unit // 클릭 이벤트를 외부에서 처리할 수 있도록 설정
) : RecyclerView.Adapter<InquiryAdapter.InquiryViewHolder>() {

    inner class InquiryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textId: TextView = itemView.findViewById(R.id.tvId)
        val inquiryTitle: TextView = itemView.findViewById(R.id.tvInquiryTitle)
        val status: TextView = itemView.findViewById(R.id.tvStatus)

        fun bind(inquiry: Inquiry) {
            textId.text = inquiry.id
            inquiryTitle.text = inquiry.title
            status.text = inquiry.isAnswered

            itemView.setOnClickListener{ onItemClick(inquiry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inquire, parent, false)
        return InquiryViewHolder(view)
    }

    override fun onBindViewHolder(holder: InquiryViewHolder, position: Int) {
        holder.bind(inquiryList[position])

    }

    override fun getItemCount(): Int = inquiryList.size
}
