package com.example.rickandmortyktfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.rickandmortyktfinal.DETAIL_IMAGE_SIZE
import com.example.rickandmortyktfinal.MainActivity
import com.example.rickandmortyktfinal.R
import com.example.rickandmortyktfinal.SELECTED_CHARACTER_IMAGE
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ZoomedPhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZoomedPhotoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(SELECTED_CHARACTER_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zoomed_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(imageUrl).resize(DETAIL_IMAGE_SIZE, DETAIL_IMAGE_SIZE).into(view.findViewById<ImageView>(R.id.image_character_zoomed))
        view.setOnClickListener {
            println("pop")
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
         * @return A new instance of fragment ZoomedPhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ZoomedPhotoFragment()
    }
}