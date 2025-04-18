package com.project.meongnyangcare2

import Pet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter(
    private val petList: List<Pet>,
    private val onItemClick: (Pet) -> Unit // 클릭 이벤트를 외부에서 처리할 수 있도록 설정
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    inner class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textId: TextView = itemView.findViewById(R.id.tvId)
        val petImage: ImageView = itemView.findViewById(R.id.imgPet)
        val petName: TextView = itemView.findViewById(R.id.tvPetNameValue)
        val petSpecies: ImageView = itemView.findViewById(R.id.tvPetSpeciesValue)
        val petAge: TextView = itemView.findViewById(R.id.tvPetAgeValue)
        val petGender: ImageView = itemView.findViewById(R.id.tvPetGenderValue)

        fun bind(pet: Pet) {
            textId.text = pet.id
            Glide.with(itemView.context)
                .load(pet.petimageUrl)
                .into(petImage)
            petName.text = pet.name
            Glide.with(itemView.context)
                .load(pet.speciesimageUrl)
                .into(petSpecies)
            petAge.text = "${pet.age}살"
            Glide.with(itemView.context)
                .load(pet.genderimageUrl)
                .into(petGender)

            itemView.setOnClickListener{ onItemClick(pet) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(petList[position])

    }

    override fun getItemCount(): Int = petList.size
}
