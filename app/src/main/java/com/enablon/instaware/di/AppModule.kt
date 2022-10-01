package com.enablon.instaware.di

import com.enablon.instaware.domain.usecase.GetPostsUseCase
import com.enablon.instaware.presentation.ui.postsScreen.PostsViewModel
import com.enablon.instaware.presentation.ui.postsScreen.list.PostsListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Application modules used by dependency injection.
 */
object AppModule {
    // View models
    val viewModelModule = module {
        viewModel { PostsViewModel() }
    }

    // Use cases
    val useCasesModule = module {
        factory { GetPostsUseCase(get()) }
    }

    // UI components
    val uiComponentModule = module {
        // Posts list adapter
        factory { params -> PostsListAdapter(get()) }
    }
}