package com.example.rickandmortyktfinal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyktfinal.API
import com.example.rickandmortyktfinal.BASE_URL
import com.example.rickandmortyktfinal.MyAdapter
import com.example.rickandmortyktfinal.dataClass.Character
import com.example.rickandmortyktfinal.R
import com.example.rickandmortyktfinal.dataClass.Page
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterListFragment : Fragment() {
    lateinit var service : API

    var characterList = ArrayList<Character>()

    lateinit var mAdapter : MyAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var viewManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_character_list)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        service = retrofit.create(API::class.java)


        val callCharacterList= service.getCharacterPageX(1)
        callCharacterList.enqueue(object : Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                response.body()?.characters?.let { characterList.addAll(it) }
                mAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        viewManager = LinearLayoutManager(view.context)
        mAdapter = MyAdapter(characterList)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter

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
        fun newInstance() = CharacterListFragment()
    }
}