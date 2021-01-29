package com.example.endocrinologia.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endocrinologia.repository.Calculos

class TIGViewModel: ViewModel() {

    private val calculos = Calculos()

    private val _resultadoTIG: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getresultadoTIG() = _resultadoTIG as LiveData<String>
    fun setresultadoTIG(tgl: String, glu: String){
        val c = calculos.tYG(tgl.toDouble(), glu.toDouble())
        if(c>4.94){
            _resultadoTIG?.value = c.format(2) + ccInsulina
        }else{
            _resultadoTIG?.value = c.format(2)
        }

    }


    companion object{
        const val ccInsulina = "\nProbable resistencia a la Insulina"
        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }
}