package com.enablon.instaware.presentation.ui.postsScreen.list

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ablanco.zoomy.Zoomy
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
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
        private val activity: Activity?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaPost: MediaPost) {
            with(binding) {
                mediaTimeTextView.text = getReadableTimeDate(mediaPost.timestamp)
                mediaUsernameTextView.text = mediaPost.username
                mediaPost.caption?.let { caption ->
                    mediaCaptionTextView.apply {
                        isVisible = true
                        text = "❝  ${caption}  ❞"
                    }
                }

                var zoomableView : View? = null
                mediaPost.children?.data?.takeIf { it.isNotEmpty() }?.let { imagesList ->
                    val urlList =
                        imagesList.filter { it.mediaUrl != null }
                            .map { SlideModel(it.mediaUrl!!, null, ScaleTypes.FIT) }
                    if (urlList.isNotEmpty()) {
                        mediaImagesCarousel.apply {
                            isVisible = true
                            setImageList(urlList)
                            zoomableView=this
                        }
                    }
                } ?: mediaPost.mediaUrl?.let {
                    mediaImageView.apply {
                        loadUrl(it)
                        isVisible = true
                        zoomableView = this
                    }
                }

                activity?.let {
                    zoomableView?.let {
                        val builder: Zoomy.Builder = Zoomy.Builder(activity).target(zoomableView)
                        builder.register()
                    }
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