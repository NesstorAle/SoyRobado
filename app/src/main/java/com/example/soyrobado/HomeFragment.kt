package com.example.soyrobado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soyrobado.databinding.FragmentHomeBinding
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    var i= 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        GlobalScope.launch(Dispatchers.Default) {
            while(true){
                binding.text.text = "${i++}"
                delay(1000)
            }
        }

        return binding.root
    }

}