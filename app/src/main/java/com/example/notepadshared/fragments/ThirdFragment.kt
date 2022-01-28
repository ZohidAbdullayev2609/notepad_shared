package com.example.notepadshared.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notepadshared.R
import com.example.notepadshared.databinding.FragmentThirdBinding
import com.example.notepadshared.model.User
import com.example.notepadshared.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var list: ArrayList<User>
    var pos = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentThirdBinding.inflate(inflater, container, false)

        MySharedPrefernce.init(requireContext())

        binding.imageBack2.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FirstFragment())?.commit()
        }

        list = ArrayList()
        val gson = Gson()
        val type = object : TypeToken<ArrayList<User>>() {}.type
        val position = arguments?.getInt("position")
        pos = position!!
        val userData = MySharedPrefernce.userData
        list = gson.fromJson(userData, type)

        binding.editTitle2.setText(list[position].title)
        binding.editDescription2.setText(list[position].description)

        binding.imageCheck2.setOnClickListener {
            val title = binding.editTitle2.text.toString()
            val description = binding.editDescription2.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                list[pos].title = title
                list[pos].description = description
                val gson = Gson()

                val sNew = gson.toJson(list)
                MySharedPrefernce.userData = sNew

                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, FirstFragment())?.commit()
            } else {
                Toast.makeText(requireContext(), "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.imageDelete2.setOnClickListener {
            val position = arguments?.getInt("position")
            pos = position!!
            list = ArrayList()
            val gson = Gson()
            val type = object : TypeToken<ArrayList<User>>() {}.type
            val user = MySharedPrefernce.userData
            list = gson.fromJson(user, type)
            list.removeAt(position)

            val sNew = gson.toJson(list, type)
            MySharedPrefernce.userData = sNew

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FirstFragment())?.commit()
        }

        return binding.root
    }

}