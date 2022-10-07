package com.enablon.instaware.presentation.ui.postsScreen.list

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ablanco.zoomy.Zoomy
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.enablon.instaware.common.utils.getReadableTimeDate
import com.enablon.instaware.common.utils.loadUrl
import com.enablon.instaware.databinding.MediaListItemBinding
import com.enablon.instaware.domain.model.media.MediaPost
import org.koin.core.component.KoinComponent

/**
 * Media list adapter handling the display and management of the Media posts
 */
class MediaListAdapter :
    ListAdapter<MediaPost, MediaListAdapter.MediaListItemViewHolder>(
        MediaPost.DiffCallback()
    ), KoinComponent {

    /**
     * An activity instance used for zooming
     */
    var activity: Activity? = null

    /**
     * Handle the binding, display and behavior of an item of the mediaList
     */
    class MediaListItemViewHolder(
        private val binding: MediaListItemBinding,
        private val activity: Activity?
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(mediaPost: MediaPost) {
            with(binding) {
                // Transform the mediaPost's timestamp to a readable text
                // Display the result date and time
                mediaTimeTextView.text = getReadableTimeDate(mediaPost.timestamp)

                // Display the publisher's username
                mediaUsernameTextView.text = mediaPost.username

                // Display the caption if it exists
                mediaPost.caption?.let { caption ->
                    mediaCaptionTextView.apply {
                        isVisible = true
                        text = "❝  $caption  ❞"
                    }
                }

                // a variable containing the view to zoom (ImageView for IMAGE post type or ImageSlider for CAROUSEL)
                var zoomableView: View? = null

                // If the mediaPost children list is not empty (CAROUSEL), fetch their URLs and feed te the ImageSlider widget
                mediaPost.children?.data?.takeIf { it.isNotEmpty() }?.let { imagesList ->
                    val urlList =
                        imagesList.filter { it.mediaUrl != null }
                            .map { SlideModel(it.mediaUrl!!, null, ScaleTypes.FIT) }
                    if (urlList.isNotEmpty()) {
                        mediaImagesCarousel.apply {
                            isVisible = true
                            setImageList(urlList)
                            zoomableView = this
                        }
                    }
                } ?:
                // Otherwise (the post is of type TMAGE), load the mediaUrl image into the ImageView
                mediaPost.mediaUrl?.let {
                    mediaImageView.apply {
                        loadUrl(it)
                        isVisible = true
                        zoomableView = this
                    }
                }

                // Register the image zooming feature
                activity?.let {
                    zoomableView?.let {
                        val builder: Zoomy.Builder = Zoomy.Builder(activity).target(zoomableView)
                        builder.register()
                    }
                }

                // Display the LikeButton value
                // Give a random value to the Liked button
                // TODO: handle when local storage is implemented
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