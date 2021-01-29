package com.example.endocrinologia.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.endocrinologia.databinding.FragmentFaiBinding
import com.example.endocrinologia.models.FaiViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class FaiFragment : Fragment() {

    private var _binding: FragmentFaiBinding? = null
    private val binding get() = _binding!!

    private val mViewModel by lazy {
        ViewModelProvider(this).get(FaiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFaiBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultados().observe(viewLifecycleOwner,{
            binding.textResultadoFAI.text = it
        })

        binding.buttonFAI.setOnClickListener {
            if(!binding.editTextTestosterona.text.isNullOrEmpty() && !binding.editTextSHBG.text.isNullOrEmpty()){
                mViewModel.setresultados(binding.editTextTestosterona.text.toString(), binding.editTextSHBG.text.toString())
                UIUtil.hideKeyboard(this.activity)
            }else{
                Toast.makeText(
                    context,
                    "Debe llenar los campos de Testosterona y SHBG",
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