package demo.bookssample.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import demo.bookssample.databinding.FragmentSettingsBinding
import demo.bookssample.di.Injectable


class SettingsFragment : Fragment(), Injectable {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
}