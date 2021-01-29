package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class NodulosTiroideosViewModel : ViewModel() {
    private val calculos = Calculos()

    private val _resultados: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultados() = _resultados as LiveData<String>
    fun setresultados(
        mixed: Boolean, solid: Boolean, calcificacion: Boolean,
        hiperecoico: Boolean, hipoecoico: Boolean, mhipoecoico: Boolean, indeterminado: Boolean,
        masAlto: Boolean,
        lobulado: Boolean, extraT: Boolean,
        macroCalc: Boolean, calcificaciones: Boolean, punteado: Boolean
    ) {
        val i = calculos.nodulos(
            mixed, solid, calcificacion, hiperecoico, hipoecoico, mhipoecoico, indeterminado,
            masAlto, lobulado, extraT, macroCalc, calcificaciones, punteado
        )

        _resultados?.value = when (i) {
            0 -> ccBenigno
            in 1..2 -> ccNoSospechoso
            3 -> ccSospechoso
            in 4..6 -> ccModerado
            else -> ccMaligno
        }
    }

    companion object{
        const val ccBenigno = "TR1 Benigno"
        const val ccNoSospechoso = "TR2 No sospechoso de malignidad"
        const val ccSospechoso = "TR3 Ligeramente sospechoso\nde malignidad"
        const val ccModerado = "TR4 Moderadamente sospechoso\nde malignidad"
        const val ccMaligno = "TR5 Altamente sospechoso\nde malignidad"

    }
}
