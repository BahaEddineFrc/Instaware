package com.enablon.instaware.presentation.ui.postsScreen.list

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ablanco.zoomy.Zoomy
import com.enablon.instaware.common.utils.getReadableTimeDate
import com.enablon.instaware.common.utils.loadUrl
import com.enablon.instaware.databinding.MediaListItemBinding
import com.enablon.instaware.domain.model.MediaPost
import org.koin.core.component.KoinComponent

class MediaListAdapter(private val context: Context) :
    ListAdapter<MediaPost, MediaListAdapter.MediaListItemViewHolder>(
        MediaPost.DiffCallback()
    ), KoinComponent {

    var activity: Activity? = null

    class MediaListItemViewHolder(
        private val binding: MediaListItemBinding,
        val activity: Activity?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaPost: MediaPost) {
            with(binding) {
                mediaTimeTextView.text = getReadableTimeDate(mediaPost.timestamp)
                mediaUsernameTextView.text = mediaPost.username
                mediaPost.caption?.let {
                    mediaCaptionTextView.apply {
                        isVisible = true
                        text = "❝ ${it} ❞"
                    }
                }

                mediaImageView.loadUrl(mediaPost.mediaUrl)

                activity?.let {
                    val builder: Zoomy.Builder = Zoomy.Builder(activity).target(mediaImageView)
                    builder.register()
                }

                mediaLikeButton.isChecked = listOf(true, false).random()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MediaListItemViewHolder(
            MediaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            activity
        )


    override fun onBindViewHolder(holder: MediaListItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}