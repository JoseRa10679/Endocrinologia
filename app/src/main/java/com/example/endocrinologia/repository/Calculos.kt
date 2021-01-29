package com.example.endocrinologia.repository

import kotlin.math.ln
import kotlin.math.log10

class Calculos {


    fun quicki(FInsulin: Double, FGlucose: Double): Double{
        return 1 / (log10(FInsulin) + log10(FGlucose))
    }

    fun aC1(bSugar: Int):Double{
        return (bSugar+46.7)/28.7
    }

    fun homa(i: Double, g: Double): Double{
        return (i * g) / 405
    }

    fun tYG(FTgl: Double, FGlucose: Double): Double{
        // FTgl en mg/dl
        // FGlucose mg/dl
        return ln (FTgl * FGlucose) / 2
    }

    fun calcio(Calcio: Double, Albumina: Double): Double{
        return Calcio + 0.8 * (4 - Albumina)
    }

    fun fAI(tTotal: Double, sHBG: Double): Double{
        return tTotal * 100 / sHBG
    }

    fun nodulos(mixed:Boolean, solid: Boolean, calcificacion: Boolean,
                hiperecoico:Boolean, hipoecoico:Boolean, mhipoecoico: Boolean, indeterminado:Boolean,
                masAlto: Boolean,
                lobulado: Boolean, extraT:Boolean,
                macroCalc:Boolean, calcificaciones:Boolean, punteado: Boolean): Int{

        var total = 0
        if(mixed){total += 1}
        if (solid){total += 2}
        if (calcificacion){total +=2}

        if(hiperecoico){total +=1}
        if (hipoecoico){total +=2}
        if (mhipoecoico){total +=3}
        if (indeterminado){total +=1}

        if (masAlto){total +=3}
        if (lobulado){total +=2}
        if (extraT){total +=3}

        if (macroCalc){total +=1}
        if (calcificaciones){ total +=2}
        if (punteado){total +=3}

        return total

    }

    fun ttKG(Kp: Double, Op: Double, Ko:Double, Oo: Double): Double{
        return (Kp*Oo)/(Ko*Op)

        /*
        * <4 Ausencia de actividad mineralocoarticoide en TCD
        * >7 Pressencia de actividad mineralocoarticoide en TCD
        * */

    }

    fun hiperaldosteronismo(aLDS: Double, aRP: Double ): Double{
        return aLDS/aRP
    }


}