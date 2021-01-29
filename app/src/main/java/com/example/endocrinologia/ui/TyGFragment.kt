package com.example.endocrinologia.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.endocrinologia.databinding.FragmentTyGBinding
import com.example.endocrinologia.models.TIGViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class TyGFragment : Fragment() {

    private var _binding: FragmentTyGBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: TIGViewModel by lazy {
        ViewModelProvider(this).get(TIGViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTyGBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultadoTIG().observe(viewLifecycleOwner,{
            binding.textResultadoTIG.text = it
        })

        binding.btnTIG.setOnClickListener {
            if(!binding.editTextTgl.text.isNullOrEmpty() && !binding.editTextGlucosa.text.isNullOrEmpty()){
                mViewModel.setresultadoTIG(binding.editTextTgl.text.toString(), binding.editTextGlucosa.text.toString())
                UIUtil.hideKeyboard(this.activity)
            }else{
                Toast.makeText(
                    context,
                    "Debe llenar los campos de Triglicéridos y Glucosa",
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