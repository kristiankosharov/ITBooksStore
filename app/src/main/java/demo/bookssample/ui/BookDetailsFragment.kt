package demo.bookssample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import demo.bookssample.databinding.FragmentBookDetailBinding
import demo.bookssample.di.Injectable


class BookDetailsFragment : Fragment(), Injectable {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
