package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class HiperAldViewModel: ViewModel() {
    private val calculos = Calculos()

    private val _resultados: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultados() = _resultados as LiveData<String>
    fun setresultados(a: String, r: String){
        val i = calculos.hiperaldosteronismo(a.toDouble(),r.toDouble())
        if(a.toDouble()>15.0 && i>30.0){
            _resultados?.value = i.format(2) + ccPos
        }else{
            _resultados?.value = i.format(2) + ccNeg
        }
    }



    companion object{
        const val ccPos = "\nPositivo"
        const val ccNeg = "\nNegativo"
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }
}