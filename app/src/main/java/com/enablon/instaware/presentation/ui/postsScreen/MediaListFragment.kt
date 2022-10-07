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
        mediaListAdapter.activity = this.activity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTransactionsRV(view.context)

        setupListeners(view.context)

        addScrollerListener()
    }

    /**
     * Setup the media posts adapter and recyclerView
     */
    private fun setupTransactionsRV(context: Context) {
        _binding.customersRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mediaListAdapter
        }
    }

    private fun addScrollerListener() {
        //attaches scrollListener with RecyclerView
        _binding.customersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getMediaList()
                }
            }
        })
    }

    private fun setupListeners(context: Context) {
        showLoader(true)
        viewModel.mediaPosts.observe(viewLifecycleOwner) {
            mediaListAdapter.submitList(it)
            showLoader(false)
            _binding.customersRv.isVisible = true
        }
        viewModel.error.observe(viewLifecycleOwner) {
            showLoader(false)
            _binding.emptyPlaceholder.isVisible = true
            _binding.customersListErrorTv.text = it
        }
        viewModel.newQuote.observe(viewLifecycleOwner) {
            Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoader(isVisible: Boolean = true) {
        _binding.progressBar.isVisible = isVisible
    }

}