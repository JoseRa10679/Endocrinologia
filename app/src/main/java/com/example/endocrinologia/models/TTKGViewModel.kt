package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class TTKGViewModel: ViewModel() {
    private val calculos = Calculos()

    private val _resultados: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultados() = _resultados as LiveData<String>
    fun setresultados(pp: String, op: String, po: String, oo: String){
        val i = calculos.ttKG(pp.toDouble(),op.toDouble(),po.toDouble(),oo.toDouble())
        val m = i.format(2)
        when{
            i < 4 -> _resultados?.value = "$m$tt4"
            i > 7 -> _resultados?.value = "$m$tt7"
            else -> _resultados?.value = "$m$tt47"
        }

    }

    companion object{
        const val tt4 = "\nSin actividad MC en TCD"
        const val tt47 = "\nNormal"
        const val tt7 = "\nCon activicad MC en TCD"

        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }

}