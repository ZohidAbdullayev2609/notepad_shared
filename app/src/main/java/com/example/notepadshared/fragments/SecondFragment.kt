package com.example.notepadshared.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notepadshared.R
import com.example.notepadshared.databinding.FragmentSecondBinding
import com.example.notepadshared.model.User
import com.example.notepadshared.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        MySharedPrefernce.init(requireContext())

        binding.imageBack.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FirstFragment())?.commit()
        }

        list = ArrayList()
        binding.imageCheck.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val gson = Gson()
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val userData = MySharedPrefernce.userData
                if (userData != null) {
                    list = gson.fromJson(userData, type)
                }
                list.add(User(title, description))
                val sNew = gson.toJson(list)
                MySharedPrefernce.userData = sNew

                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, FirstFragment())?.commit()
            } else {
                Toast.makeText(requireContext(), "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

}