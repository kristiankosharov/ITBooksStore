package demo.bookssample.ui.newreleased

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import demo.bookssample.AppExecutors
import demo.bookssample.R
import demo.bookssample.databinding.ItemBookLayoutBinding
import demo.bookssample.entity.Book
import demo.bookssample.ui.common.DataBoundViewHolder


class NewReleasedAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors
) : ListAdapter<Book, DataBoundViewHolder<ItemBookLayoutBinding>>(
        AsyncDifferConfig.Builder<Book>(
                object : DiffUtil.ItemCallback<Book>() {
                    // Concert details may have changed if reloaded from the database,
                    // but ID is fixed.
                    override fun areItemsTheSame(oldBook: Book,
                                                 newBook: Book): Boolean =
                            oldBook.isbn13 == newBook.isbn13


                    override fun areContentsTheSame(oldBook: Book,
                                                    newBook: Book): Boolean =
                            oldBook == newBook
                })
                .setBackgroundThreadExecutor(appExecutors.diskIO())
                .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ItemBookLayoutBinding> {
        val binding = createBinding(parent)
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemBookLayoutBinding>, position: Int) {
        val book = getItem(position)
        bind(holder.binding, book)
    }

    private fun bind(binding: ItemBookLayoutBinding, item: Book?) {
        binding.book = item
    }

    private fun createBinding(parent: ViewGroup): ItemBookLayoutBinding {
        val binding = DataBindingUtil
                .inflate<ItemBookLayoutBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_book_layout,
                        parent,
                        false,
                        dataBindingComponent
                )
        binding.root.setOnClickListener {
            //            binding.contributor?.let {
//                callback?.invoke(it)
//            }
        }
        return binding
    }
}