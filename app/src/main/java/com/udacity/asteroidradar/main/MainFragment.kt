package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.asteroidradar.AsteroidsApplication
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.recyclerViewAdapter.AsteroidsRecyclerViewAdapter
import com.udacity.asteroidradar.recyclerViewAdapter.ClickListener
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory(
            (requireActivity().application as AsteroidsApplication).asteroidsRepository,
            (requireActivity().application as AsteroidsApplication).pictureOfDayRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.asteroidRecycler.adapter = AsteroidsRecyclerViewAdapter(ClickListener { asteroid ->
            viewModel.navigateToAsteroidDetailsFragment(asteroid)
        })

        viewModel.eventNavigateToAsteroidDetailFragment.observe(viewLifecycleOwner, Observer { asteroid ->
            if(asteroid != null){
                findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onNavigatedToAsteroidDetailFragment()
            }
        })
        viewModel.onRefreshDatabaseFailed.observe(viewLifecycleOwner, Observer { onFailed ->
            if (onFailed) {
                Snackbar.make(
                    requireView(),
                    getString(R.string.snack_bar_text),
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.onRefreshDatabaseFailureHandled()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_today_asteroids -> viewModel.onTodayAsteroidsClicked()
            R.id.show_saved_asteroids -> viewModel.onSavedAsteroidsClicked()
            R.id.show_week_asteroids -> viewModel.onWeekAsteroidsClicked()
        }
        return true
    }
}
