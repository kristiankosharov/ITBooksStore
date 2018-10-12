package demo.bookssample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import demo.bookssample.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}