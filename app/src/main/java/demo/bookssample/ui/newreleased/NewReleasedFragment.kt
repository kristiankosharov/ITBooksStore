package demo.bookssample.ui.newreleased

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import demo.bookssample.databinding.FragmentNewReleasedBinding
import demo.bookssample.db.BooksDb
import demo.bookssample.di.Injectable
import javax.inject.Inject


class NewReleasedFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var booksViewModel: NewReleasedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewReleasedBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val TAG: String? = NewReleasedFragment::class.simpleName

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        booksViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewReleasedViewModel::class.java)

        booksViewModel.getNewBooks().observe(this, Observer { newReleased ->
            Log.d(TAG, newReleased.data.toString())
        })
    }
}