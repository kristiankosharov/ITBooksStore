package demo.bookssample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import demo.bookssample.databinding.FragmentNewReleasedBinding
import demo.bookssample.di.Injectable


class NewReleasedFragment : Fragment(), Injectable {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewReleasedBinding.inflate(inflater, container, false)
        return binding.root
    }
}