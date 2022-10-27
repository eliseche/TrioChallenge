package com.eliseche.triochallenge.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliseche.triochallenge.R
import com.eliseche.triochallenge.data.models.Item
import com.eliseche.triochallenge.data.models.Menu
import com.eliseche.triochallenge.databinding.FragmentRestaurantBinding
import com.eliseche.triochallenge.databinding.LayoutMenuBinding
import com.eliseche.triochallenge.ui.restaurantDetail.RestaurantDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var menuAdapter: RestaurantMenuAdapter
    private var restaurantDetailDialogFragment: RestaurantDetailFragment? = null

    companion object {
        fun newInstance() = RestaurantFragment()
    }

    //region Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRestaurantBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }
    //endregion

    //region Init
    private fun init() {
        viewModel.init()

        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collectLatest { uiEvent ->
                when (uiEvent) {
                    is RestaurantUiEvent.Menus -> buildMenuUi(uiEvent.menu)
                    is RestaurantUiEvent.Error -> showMessage(uiEvent.message)
                    is RestaurantUiEvent.NavigateToRestaurantDetail -> navigateDetail(uiEvent.item)
                }
            }
        }
    }
    //endregion

    private fun buildMenuUi(menus: List<Menu>) {
        lifecycleScope.launch {
            binding.menusContainer.removeAllViews()

            menus.forEach { menu ->
                val viewMenuBinding = LayoutMenuBinding.inflate(layoutInflater)

                // Title
                viewMenuBinding.menuTitle.text = menu.name

                // Horizontal List
                menuAdapter = RestaurantMenuAdapter(viewModel::onEvent)
                menuAdapter.setItems(menu.items)

                viewMenuBinding.menu.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                viewMenuBinding.menu.adapter = menuAdapter

                binding.menusContainer.addView(viewMenuBinding.root)
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun navigateDetail(menu: Item) {
        restaurantDetailDialogFragment = RestaurantDetailFragment()
        val arguments = Bundle()
        arguments.putSerializable("item", menu)
        restaurantDetailDialogFragment?.arguments = arguments
        restaurantDetailDialogFragment?.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Modal)
        restaurantDetailDialogFragment?.show(childFragmentManager, RestaurantDetailFragment::class.java.simpleName)
    }
}