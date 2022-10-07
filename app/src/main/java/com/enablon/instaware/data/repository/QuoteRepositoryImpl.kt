package com.enablon.instaware.data.repository

import com.enablon.instaware.data.remote.quote.QuoteServices
import com.enablon.instaware.domain.repository.QuoteRepository
import org.koin.core.component.KoinComponent

class QuoteRepositoryImpl(private val services: QuoteServices) : QuoteRepository,
    KoinComponent {
    override suspend fun getNewQuote() = services.getNewQuote()
}