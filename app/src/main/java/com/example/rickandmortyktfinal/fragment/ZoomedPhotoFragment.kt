package com.example.rickandmortyktfinal.fragment

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.rickandmortyktfinal.DETAIL_IMAGE_SIZE
import com.example.rickandmortyktfinal.R
import com.example.rickandmortyktfinal.SELECTED_CHARACTER_IMAGE
import com.example.rickandmortyktfinal.VIEW_NAME_HEADER_IMAGE
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
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
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
            activity?.onBackPressed()
        }
    }

    /*private fun addTransitionListener(): Boolean {
        val transition = window.sharedElementEnterTransition
        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    loadFullSizeImage()

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }*/


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