package demo.bookssample.ui.newreleased

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import demo.bookssample.AppExecutors
import demo.bookssample.binding.FragmentDataBindingComponent
import demo.bookssample.databinding.FragmentNewReleasedBinding
import demo.bookssample.di.Injectable
import demo.bookssample.util.autoCleared
import javax.inject.Inject


class NewReleasedFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var booksViewModel: NewReleasedViewModel
    private var adapter by autoCleared<NewReleasedAdapter>()
    var binding by autoCleared<FragmentNewReleasedBinding>()
    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewReleasedBinding.inflate(inflater, container, false)
        this.binding = binding

        return binding.root
    }

    private val TAG: String? = NewReleasedFragment::class.simpleName

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        booksViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewReleasedViewModel::class.java)

        val adapter = NewReleasedAdapter(dataBindingComponent, appExecutors)
        this.adapter = adapter
        binding.newReleasedBooks.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(activity, VERTICAL)
        binding.newReleasedBooks.addItemDecoration(dividerItemDecoration)
        booksViewModel.books.observe(this, Observer { newReleased ->
            Log.d(TAG, newReleased.data.toString())
            adapter.submitList(newReleased.data?.books)
        })
    }
}