package com.enablon.instaware.domain.model

data class Paging(
    val cursors: Cursor,
    val previous: String?,
    val next: String?
)