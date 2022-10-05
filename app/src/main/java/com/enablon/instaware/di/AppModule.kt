package com.enablon.instaware.di

import com.enablon.instaware.data.remote.provideInstagramApi
import com.enablon.instaware.data.remote.provideRetrofitClient
import com.enablon.instaware.data.repository.MediaRepositoryImpl
import com.enablon.instaware.domain.repository.MediaRepository
import com.enablon.instaware.domain.usecase.GetMediaListUseCase
import com.enablon.instaware.presentation.ui.postsScreen.MediaListViewModel
import com.enablon.instaware.presentation.ui.postsScreen.list.MediaListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Application modules used by dependency injection.
 */
object AppModule {
    // View models
    val viewModelModule = module {
        viewModel { MediaListViewModel() }
    }

    // Use cases
    val useCasesModule = module {
        factory { provideRetrofitClient() }
        factory { provideInstagramApi() }
        single<MediaRepository> { MediaRepositoryImpl(get()) }
        factory { GetMediaListUseCase(get()) }
    }

    // UI components
    val uiComponentModule = module {
        // Posts list adapter
        factory { MediaListAdapter(get()) }
    }
}