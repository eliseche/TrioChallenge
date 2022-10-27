package com.eliseche.triochallenge.ui.restaurantDetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.eliseche.triochallenge.data.models.Item
import com.eliseche.triochallenge.databinding.FragmentRestaurantDetailBinding
import com.eliseche.triochallenge.extensions.getCustomSerializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : DialogFragment() {
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var binding: FragmentRestaurantDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRestaurantDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getCustomSerializable<Item>("item")
        item?.let {
            init(it)
        }
    }

    override fun onResume() {
        super.onResume()

        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setGravity(Gravity.BOTTOM)
    }

    //region Init
    private fun init(item: Item) {
        buildUi(item)
    }
    //endregion

    private fun buildUi(item: Item) {
        binding.apply {
            Glide
                .with(requireContext())
                .load(item.url)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(logo)

            title.text = item.name
            price.text = "$${item.price}"
            description.text = item.description
        }
    }
}