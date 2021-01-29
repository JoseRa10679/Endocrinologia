package com.example.endocrinologia.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.endocrinologia.ui.HiperAldFragment
import com.example.endocrinologia.ui.GradienteTTKFragment
import com.example.endocrinologia.ui.NodulosTFragment
import com.example.endocrinologia.ui.CalcioFragment
import com.example.endocrinologia.ui.FaiFragment
import com.example.endocrinologia.ui.MetabHFragment
import com.example.endocrinologia.ui.TyGFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> MetabHFragment()
            1 -> TyGFragment()
            2 -> CalcioFragment()
            3 -> FaiFragment()
            4 -> NodulosTFragment()
            5 -> GradienteTTKFragment()
            else -> HiperAldFragment()
        }


    }
}