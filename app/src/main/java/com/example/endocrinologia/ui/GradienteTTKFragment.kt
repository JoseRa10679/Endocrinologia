package com.example.endocrinologia.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.endocrinologia.databinding.FragmentGradienteTTKBinding
import com.example.endocrinologia.models.TTKGViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class GradienteTTKFragment : Fragment() {

    private var _binding: FragmentGradienteTTKBinding? = null
    private val binding get() = _binding!!

    private val mViewModel by lazy {
        ViewModelProvider(this).get(TTKGViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGradienteTTKBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultados().observe(viewLifecycleOwner,{
            binding.textResultadoTTG.text = it
        })

        binding.buttonCalcullaGTTK.setOnClickListener {
            if(binding.editTextKOrina.text.isNotEmpty() && binding.editTextKPlasma.text.isNotEmpty() &&
                binding.editTextOOrina.text.isNotEmpty() && binding.editTextOPlasma.text.isNotEmpty()){
                mViewModel.setresultados(binding.editTextKPlasma.text.toString(), binding.editTextOPlasma.text.toString(),
                    binding.editTextKOrina.text.toString(), binding.editTextOOrina.text.toString())

                UIUtil.hideKeyboard(this.activity    )
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