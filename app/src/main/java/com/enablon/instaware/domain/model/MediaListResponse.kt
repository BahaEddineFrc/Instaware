package com.enablon.instaware.domain.model

data class MediaListResponse(
    private val data : List<Media>,
    private val paging: Paging
)