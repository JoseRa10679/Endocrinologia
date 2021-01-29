package com.example.endocrinologia.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.endocrinologia.databinding.FragmentHiperAldBinding
import com.example.endocrinologia.models.HiperAldViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class HiperAldFragment : Fragment() {

    private var _binding: FragmentHiperAldBinding? = null
    private val binding get() = _binding!!

    private val mViewModel by lazy {
        ViewModelProvider(this).get(HiperAldViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHiperAldBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultados().observe(viewLifecycleOwner, {
            binding.textResultadoHiperAld.text = it
        })

        binding.buttonCalculaHiperAld.setOnClickListener {
            if(binding.editTextAldosterna.text.isNotEmpty() && binding.editTextRenina.text.isNotEmpty()){
                mViewModel.setresultados(binding.editTextAldosterna.text.toString(), binding.editTextRenina.text.toString())
                UIUtil.hideKeyboard(this.activity)
            }else{
                Toast.makeText(
                    context,
                    "Debe llenarse todos campos.",
                    Toast.LENGTH_LONG
                ).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }
            }
        }


        return view
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}