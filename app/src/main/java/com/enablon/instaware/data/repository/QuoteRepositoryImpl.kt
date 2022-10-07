package com.enablon.instaware.data.repository

import com.enablon.instaware.data.remote.quote.QuoteServices
import com.enablon.instaware.domain.repository.QuoteRepository
import org.koin.core.component.KoinComponent

/**
 * The implementation of the [QuoteRepository] methods
 */
class QuoteRepositoryImpl(private val services: QuoteServices) : QuoteRepository,
    KoinComponent {
    /**
     * Request a new random quote
     */
    override suspend fun getNewQuote() = services.getNewQuote()
}