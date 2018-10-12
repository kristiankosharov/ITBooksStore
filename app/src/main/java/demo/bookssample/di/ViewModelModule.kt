package demo.bookssample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import demo.bookssample.ui.newreleased.NewReleasedViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewReleasedViewModel::class)
    abstract fun bindNewBooksViewModel(newReleasedViewModel: NewReleasedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: BooksViewModelFactory): ViewModelProvider.Factory
}