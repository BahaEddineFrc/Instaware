package com.enablon.instaware.presentation.ui.postsScreen.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enablon.instaware.common.utils.getReadableTimeDate
import com.enablon.instaware.common.utils.loadUrl
import com.enablon.instaware.databinding.MediaListItemBinding
import com.enablon.instaware.domain.model.MediaPost
import org.koin.core.component.KoinComponent

class MediaListAdapter(private val context: Context) :
    ListAdapter<MediaPost, MediaListAdapter.MediaListItemViewHolder>(
        MediaPost.DiffCallback()
    ), KoinComponent {

    class MediaListItemViewHolder(
        private val binding: MediaListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaPost: MediaPost) {
            with(binding) {
                mediaTimeTextView.text = getReadableTimeDate(mediaPost.timestamp)
                mediaUsernameTextView.text = mediaPost.username
                mediaCaptionTextView.text = mediaPost.caption
                mediaImageView.loadUrl(mediaPost.mediaUrl)
                mediaHeartButton.isLiked = listOf(true,false).random()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MediaListItemViewHolder(MediaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MediaListItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}