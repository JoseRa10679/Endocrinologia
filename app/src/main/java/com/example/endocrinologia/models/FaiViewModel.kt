package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class FaiViewModel:ViewModel() {

    private val calculos = Calculos()

    private val _resultados: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultados() = _resultados as LiveData<String>
    fun setresultados(tTotal: String, sHBG: String){
        val i = calculos.fAI(tTotal.toDouble(),sHBG.toDouble())
       _resultados?.value = i.format(2)
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}