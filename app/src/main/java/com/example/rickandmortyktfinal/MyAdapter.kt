package com.example.rickandmortyktfinal

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentTransitionImpl
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyktfinal.dataClass.Character
import com.example.rickandmortyktfinal.fragment.CharacterDetailsFragment
import com.example.rickandmortyktfinal.fragment.ZoomedPhotoFragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class MyAdapter(val listCharacter: ArrayList<Character>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var mPrefs: SharedPreferences
    lateinit var prefsEditor: SharedPreferences.Editor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false) as CardView

        val mViewHolder = MyViewHolder(cardView)

        mPrefs = parent.context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        prefsEditor = mPrefs.edit()

        val savedCharacter = mPrefs.all

        return mViewHolder
    }




    override fun getItemCount(): Int {
        return listCharacter.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val me = listCharacter[position]
        holder.itemView.findViewById<TextView>(R.id.name_text_view).text = me.name

        Picasso.get().load(me.image).resize(ITEM_LIST_SIZE, ITEM_LIST_SIZE).into(holder.itemView.findViewById<ImageView>(R.id.character_image_view))

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(SELECTED_CHARACTER, me)
            val frag = CharacterDetailsFragment.newInstance()
            frag.arguments = bundle
            (it.context as MainActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, frag)
                .addToBackStack("CharacterDetailsFragment")
                .commit()

        }

        holder.itemView.setOnLongClickListener{
            val bundle = Bundle()
            bundle.putString(SELECTED_CHARACTER_IMAGE, me.image)
            val frag = ZoomedPhotoFragment.newInstance()
            frag.arguments = bundle
            val im = it.findViewById<ImageView>(R.id.character_image_view)
            ViewCompat.getTransitionName(im)?.let { it1 ->
                (it.context as MainActivity).supportFragmentManager.beginTransaction()
                        .addSharedElement(im, it1)
                    .replace(R.id.fragment_container, frag)
                    .addToBackStack("ZoomedPhotoFragment")
                    .commit()
            }
            true
        }
    }


}