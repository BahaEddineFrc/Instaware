package com.enablon.instaware.domain.model.media

/**
 * An Instagram MediaResponse paging indicator
 * used by [MediaListResponse]
 */
data class Paging(
    val cursors: Cursor,
    val previous: String?,
    val next: String?
)