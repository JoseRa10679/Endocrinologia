package com.example.endocrinologia.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.example.endocrinologia.databinding.FragmentCalcioBinding
import com.example.endocrinologia.models.CalcioViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class CalcioFragment : Fragment() {

    private var _binding: FragmentCalcioBinding? = null
    private val binding get() = _binding!!

    private val mViewModel by lazy {
        ViewModelProvider(this).get(CalcioViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalcioBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getrCalcio().observe(viewLifecycleOwner,{
            binding.textResultadoCalcio.text = it
        })

        binding.buttonCalcioC.setOnClickListener {
            if(!binding.editTextCalcio.text.isNullOrEmpty() && !binding.editTexttAlbumina.text.isNullOrEmpty()){
                mViewModel.setrCalcio(binding.editTextCalcio.text.toString(), binding.editTexttAlbumina.text.toString())
                UIUtil.hideKeyboard(this.activity)
            }else{
                Toast.makeText(
                    context,
                    "Debe llenar los campos de Calcio y Albúmina",
                    Toast.LENGTH_LONG
                ).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }
            }
        }

        with(binding.imageCalcio) {
            setOnTouchListener(ImageMatrixTouchHandler(context))
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}