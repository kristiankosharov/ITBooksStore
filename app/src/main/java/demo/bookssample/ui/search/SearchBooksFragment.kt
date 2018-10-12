package demo.bookssample.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import demo.bookssample.databinding.FragmentSearchBooksBinding
import demo.bookssample.db.BooksDb
import demo.bookssample.di.Injectable


class SearchBooksFragment : Fragment(), Injectable {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchBooksBinding.inflate(inflater, container, false)

        BooksDb.exportDB(packageName = "")
        return binding.root
    }
}