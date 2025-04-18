package com.project.meongnyangcare2

import Pet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnalysisMyPetSelectFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.setToolbarTitle("내 반려동물 진단")

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPets)

        // 목업 데이터 추가
        val petList = listOf(
            Pet("1", R.drawable.testmydog, "코코", R.drawable.clean_dog, "6", R.drawable.male),
            Pet("2", R.drawable.testmycat, "미미", R.drawable.clean_cat, "2", R.drawable.female)
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PetAdapter(petList) { pet ->
            openNextFragment(pet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analysis_my_pet_select, container, false)
    }

    private fun openNextFragment(pet: Pet) {
        val uploadFragment = UploadPetImageFragment()

        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, uploadFragment)
            .addToBackStack(null)
            .commit()
    }

}