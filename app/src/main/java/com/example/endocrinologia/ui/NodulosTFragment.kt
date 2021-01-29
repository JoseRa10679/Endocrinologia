package com.example.endocrinologia.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.endocrinologia.databinding.FragmentNodulosTBinding
import com.example.endocrinologia.models.NodulosTiroideosViewModel
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class NodulosTFragment : Fragment() {

    private var _binding: FragmentNodulosTBinding? = null
    private val binding get() = _binding!!

    private val mViewModel by lazy {
        ViewModelProvider(this).get(NodulosTiroideosViewModel::class.java)
    }

    private companion object{
        const val ccResultado = "Resultado"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNodulosTBinding.inflate(inflater, container, false)
        val view = binding.root

        UIUtil.hideKeyboard(this.activity)

        mViewModel.getresultados().observe(viewLifecycleOwner,{
            binding.textResultadoNodulos.text = it
        })

        binding.buttonCalcular.setOnClickListener {

            mViewModel.setresultados(binding.rbtMixed.isChecked,binding.rbtSolid.isChecked, binding.rbtCacificacion.isChecked,
                binding.rbtHiperecoico.isChecked, binding.rbtHipoecoico.isChecked, binding.rbtMHipoecoico.isChecked, binding.rbtEcoIndeterminado.isChecked,
                binding.rbtMasAlto.isChecked, binding.rbtLobulado.isChecked, binding.rbtExtraT.isChecked, binding.chkMacroCalc.isChecked,
                binding.chkCalcificaciones.isChecked, binding.chkPunteado.isChecked)


        }
        binding.buttonLimpiar.setOnClickListener {

            binding.run {
                rbtCistico.isChecked = false
                rbtEspongiforme.isChecked = false
                rbtMixed.isChecked = false
                rbtSolid.isChecked = false
                rbtCacificacion.isChecked = false

                rbtAnecoico.isChecked = false
                rbtHiperecoico.isChecked = false
                rbtHipoecoico.isChecked = false
                rbtMHipoecoico.isChecked = false
                rbtEcoIndeterminado.isChecked = false

                rbtMasAncho.isChecked = false
                rbtMasAlto.isChecked = false

                rbtMargenSuave.isChecked = false
                rbtMalDef.isChecked = false
                rbtLobulado.isChecked = false
                rbtExtraT.isChecked = false
                rbtIndeterminado.isChecked = false

                chkNinguno.isChecked = false
                chkMacroCalc.isChecked = false
                chkCalcificaciones.isChecked = false
                chkPunteado.isChecked = false

                textResultadoNodulos.text = ccResultado
            }

        }

        return view
    }

    override fun onResume() {
        super.onResume()
        UIUtil.hideKeyboard(this.activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}