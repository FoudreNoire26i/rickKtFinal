package com.example.rickandmortyktfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rickandmortyktfinal.*
import com.example.rickandmortyktfinal.dataClass.Character
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailsFragment : Fragment() {

    var me : Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            me = it.getSerializable(SELECTED_CHARACTER) as Character
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(me?.image).resize(DETAIL_IMAGE_SIZE, DETAIL_IMAGE_SIZE).into(view.findViewById<ImageView>(R.id.image_details))

        view.findViewById<TextView>(R.id.name_details).text = me?.name ?: "I\'m no name"
        view.findViewById<TextView>(R.id.status_details).text = me?.status ?: "I have no status"
        view.findViewById<TextView>(R.id.species_details).text = me?.species ?: "I have no species"

        view.setOnClickListener{
            (it.context as MainActivity).supportFragmentManager.popBackStack()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OtherFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CharacterDetailsFragment()
    }
}