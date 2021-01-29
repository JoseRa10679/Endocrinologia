package com.example.endocrinologia.ui

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.endocrinologia.R
import com.example.endocrinologia.adapters.ViewPagerAdapter
import com.example.endocrinologia.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy { ViewPagerAdapter(this) }

    //<editor-fold desc="Menu">
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar items
        if (item.itemId == R.id.action_settings) {
            Toast.makeText(this@MainActivity, R.string.AcercadeJosera, Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }

        } else if (item.itemId == R.id.version) {
            var packageinfo: PackageInfo? = null
            try {
                packageinfo = packageManager.getPackageInfo(packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            var version: String? = null
            if (packageinfo != null) {
                version = packageinfo.versionName
            }
            Toast.makeText(this@MainActivity, "Endocrinología versión: $version", Toast.LENGTH_SHORT).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }

        }
        return false
    }

    //</editor-fold>

    companion object{
        private const val ccMET = "Metab. Hidrocabondo"
        private const val ccTYG = "Indice TYG"
        private const val ccCAL = "Ajuste Calcio"
        private const val ccFAI = "Free Androgen Index"
        private const val ccNOD = "Nódulos Tiroideos"
        private const val ccGRAD = "Gradiente TT de K+"
        private const val ccHIP = "Hiperaldosteronismo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setContentView(root)
            setSupportActionBar(toolbar)
        }


        binding.viewPager.adapter = this.adapter

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = ccMET
                1 -> tab.text = ccTYG
                2 -> tab.text = ccCAL
                3 -> tab.text = ccFAI
                4 -> tab.text = ccNOD
                5 -> tab.text = ccGRAD
                6 -> tab.text = ccHIP
            }
        }.attach()

    }
}