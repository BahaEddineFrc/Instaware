package com.enablon.instaware.domain.model.media

data class Paging(
    val cursors: Cursor,
    val previous: String?,
    val next: String?
)