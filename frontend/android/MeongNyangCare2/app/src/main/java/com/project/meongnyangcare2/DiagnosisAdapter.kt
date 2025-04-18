package com.project.meongnyangcare2

import Diagnosis
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DiagnosisAdapter(private val diagnosisList: List<Diagnosis>, private val onItemClick: (Diagnosis) -> Unit) :
    RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewHolder>() {

    inner class DiagnosisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textId: TextView = itemView.findViewById(R.id.tvId)
        val imageId: ImageView = itemView.findViewById(R.id.imgPet)
        val textPetName: TextView = itemView.findViewById(R.id.tvPetName)
        val textDisease: TextView = itemView.findViewById(R.id.tvDisease)
        val textStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val textRisk: TextView = itemView.findViewById(R.id.tvRisk)

        fun bind(diagnosis: Diagnosis) {
            textId.text = diagnosis.id
            Glide.with(itemView.context)
                .load(diagnosis.imageUrl)
                .into(imageId)
            textPetName.text = diagnosis.petName
            textDisease.text = diagnosis.diseaseName
            textStatus.text = diagnosis.status
            textRisk.text = diagnosis.riskLevel

            itemView.setOnClickListener { onItemClick(diagnosis) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_diagnosis, parent, false)
        return DiagnosisViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiagnosisViewHolder, position: Int) {
        holder.bind(diagnosisList[position])
    }

    override fun getItemCount(): Int = diagnosisList.size
}
