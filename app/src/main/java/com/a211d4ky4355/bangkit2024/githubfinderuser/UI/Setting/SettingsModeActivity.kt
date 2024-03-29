package com.a211d4ky4355.bangkit2024.githubfinderuser.UI.Setting

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.SettingsPerference
import com.a211d4ky4355.bangkit2024.githubfinderuser.Perference.dataStore
import com.a211d4ky4355.bangkit2024.githubfinderuser.databinding.ActivitySettingsModeBinding

class SettingsModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingsPerference.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(this, SettingsModeViewsModelsFactory(pref))[SettingsModeViewsModels::class.java]

        mainViewModel.getThemeSettings().observe(this){ isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.swThemeButton.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.swThemeButton.isChecked = false
            }
        }

        binding.swThemeButton.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }
    }
}