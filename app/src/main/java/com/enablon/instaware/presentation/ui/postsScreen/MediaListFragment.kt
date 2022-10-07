package com.enablon.instaware.presentation.ui.postsScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enablon.instaware.common.utils.loge
import com.enablon.instaware.databinding.FragmentMediaListBinding
import com.enablon.instaware.presentation.ui.postsScreen.list.MediaListAdapter
import org.koin.android.ext.android.inject

class MediaListFragment : Fragment() {

    private val viewModel: MediaListViewModel by inject()
    private val mediaListAdapter: MediaListAdapter by inject()

    private lateinit var _binding: FragmentMediaListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaListBinding.inflate(inflater)
        return _binding.root
    }

    override fun onStart() {
        super.onStart()
        // feed an instance of the activity to the mediaList adapter to register its images zooming feature
        mediaListAdapter.activity = this.activity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoader(true)

        setupTransactionsRV(view.context)

        setupDataListeners(view.context)

    }

    /**
     * Setup the media posts adapter and recyclerView
     */
    private fun setupTransactionsRV(context: Context) {
        _binding.mediaListRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mediaListAdapter
            addScrollerListener()
        }
    }

    /**
     * Listen to when the RecyclerView reaches its bottom end and load more media posts
     */
    private fun addScrollerListener() {
        _binding.mediaListRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getMediaList()
                }
            }
        })
    }

    /**
     * Setup the media posts adapter and recyclerView
     */
    private fun setupDataListeners(context: Context) {
        // Listen to the mediaPosts list updates -> update the adapter and UI
        viewModel.mediaPosts.observe(viewLifecycleOwner) {
            mediaListAdapter.submitList(it)
            showLoader(false)
            _binding.mediaListRv.isVisible = true
        }
        // Listen to the mediaList request error -> update the UI
        viewModel.error.observe(viewLifecycleOwner) {
            showLoader(false)
            _binding.emptyPlaceholder.isVisible = true
            _binding.mediaListErrorTv.text = it
        }
        // Listen to the Quote request updates -> update the UI
        //viewModel.newQuote.observe(viewLifecycleOwner) {
            //Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
        //}
        _binding.fabSettings.setOnClickListener {
            viewModel.newQuote.value?.let {
                Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.timeUsageEnded.observe(viewLifecycleOwner) {
            if(it){
               // Toast.makeText(context, "Time ended", Toast.LENGTH_LONG).show()
                loge { "Time ended" }
            }
        }
    }

    /**
     * Show or hide the progressBar
     */
    private fun showLoader(isVisible: Boolean = true) {
        _binding.progressBar.isVisible = isVisible
    }

}