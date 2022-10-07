package com.enablon.instaware.domain.usecase

import com.enablon.instaware.domain.model.quote.Quote
import com.enablon.instaware.domain.repository.QuoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent

/**
 * The Quote use case handling the request and interception of the Quote API response
 */
class GetNewQuoteUseCase(private val quoteRepository: QuoteRepository) : KoinComponent {
    suspend operator fun invoke(): Single<Quote> =
        quoteRepository.getNewQuote()
            .map { res ->
                if (res.isSuccessful) res.body()!! else Quote("", "", "")
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}