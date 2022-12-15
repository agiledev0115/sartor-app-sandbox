package com.sartor.ui.fragments.user_profile_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.sartor.R
import com.sartor.data.db.SharedPreference
import com.sartor.databinding.FragmentUserProfileBinding
import com.sartor.util.constants.placeImage


class UserProfileFragment : Fragment() {

    private lateinit var sharedPreference: SharedPreference
    var binding: FragmentUserProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater)


        sharedPreference = SharedPreference(requireContext())


        binding?.settings?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_settingsFragment
            )
        }


        binding?.coupons?.setOnClickListener {
            /* Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                 R.id.action_userProfileFragment_to_couponsFragment
             )*/
            Toast.makeText(context, "coming soon...", Toast.LENGTH_SHORT).show()

        }

        binding?.giftCards?.setOnClickListener {
/*            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_giftCardsFragment

            )
*/
            Toast.makeText(context, "coming soon...", Toast.LENGTH_SHORT).show()
        }

        binding?.returns?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_returnsFragment
            )
        }

        binding?.orders?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_ordersFragment
            )
        }

        binding?.shipped?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_shippedFragment
            )
        }

        binding?.wishlist?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_wishlistFragment
            )
        }

        binding?.ivShoppingBag?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_checkoutFragment
            )
        }

        binding?.ivMessages?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_notificationFragment
            )
        }

        binding?.measurement?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.homeHost).navigate(
                R.id.action_userProfileFragment_to_measurementFragment
            )
        }

        setUpProfile()


        return binding?.root
    }

    private fun setUpProfile() {
        binding?.ivProfile?.placeImage(sharedPreference.getProfileImage())
        binding?.tvName?.text = sharedPreference.getFullName()
    }


}