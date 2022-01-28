package com.example.notepadshared.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notepadshared.R
import com.example.notepadshared.adapter.MyRecycleAdapter
import com.example.notepadshared.databinding.FragmentFirstBinding
import com.example.notepadshared.model.User
import com.example.notepadshared.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FirstFragment : Fragment(), MyRecycleAdapter.OnClick {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)

        MySharedPrefernce.init(requireContext())

//        binding.cardAdd.setOnClickListener {
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.container, SecondFragment())?.commit()
//        }

        list = ArrayList()
        val gson = Gson()
        val type = object : TypeToken<ArrayList<User>>() {}.type
        val userData = MySharedPrefernce.userData
        if (userData == null) {
            Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
        } else {
            list = gson.fromJson(userData, type)
//            binding.rv.adapter = MyRecycleAdapter(list, this)
        }

        return binding.root
    }

    override fun onClickListener(position: Int) {
        val thirdFragment = ThirdFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        thirdFragment.arguments = bundle
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, ThirdFragment())?.commit()
    }

}