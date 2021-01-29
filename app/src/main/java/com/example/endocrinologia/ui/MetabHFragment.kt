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
import com.example.endocrinologia.databinding.FragmentMetabHBinding
import com.example.endocrinologia.models.MetGViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class MetabHFragment : Fragment() {

    private var _binding: FragmentMetabHBinding? = null
    private val binding get() = _binding!!

    private val mViewModel: MetGViewModel by lazy {
        ViewModelProvider(this).get(MetGViewModel::class.java)
    }

    private companion object{
        const val cc_noInsulina = "Sin datos de Insulina"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMetabHBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultado().observe(viewLifecycleOwner,{
            binding.textResultadoAC1.text = it
        })

        mViewModel.getresultadoQUICKI().observe(viewLifecycleOwner,{
            binding.textResultadoQUIKI.text = it
        })

        mViewModel.getresultadoHOMA().observe(viewLifecycleOwner,{
            binding.textResultadoHOMA.text = it
        })



        binding.buttonQUICKI.setOnClickListener {
            if(binding.editGlucosa.text.toString().isNotEmpty()){
                if (binding.editInsulina.text.toString().isNotEmpty()){
                    mViewModel.run {
                        setresultado(binding.editGlucosa.text.toString())
                        setresultadoQUICKI(binding.editInsulina.text.toString(), binding.editGlucosa.text.toString() )
                        setresultadoHOMA(binding.editInsulina.text.toString(), binding.editGlucosa.text.toString())
                    }
                }else{
                    binding.run {
                        mViewModel.setresultado(editGlucosa.text.toString())
                        textResultadoQUIKI.text = cc_noInsulina
                        textResultadoHOMA.text = cc_noInsulina
                    }

                }
            }else{
                Toast.makeText(context,
                    "Debe llenar los campos de Insulina y/o Glucosa",
                    Toast.LENGTH_LONG
                ).apply {
                    setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    show()
                }
            }
            UIUtil.hideKeyboard(this.activity)
        }


        with(binding.imageAc1) {
            setOnTouchListener(ImageMatrixTouchHandler(context))
        }

        return view
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}