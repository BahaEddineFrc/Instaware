package com.enablon.instaware.di

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
        factory { GetMediaListUseCase(get()) }
    }

    // UI components
    val uiComponentModule = module {
        // Posts list adapter
        factory { MediaListAdapter(get()) }
    }
}