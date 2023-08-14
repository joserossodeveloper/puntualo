package com.example.puntualo.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.puntualo.Login
import com.example.puntualo.UserForm
import com.example.puntualo.databinding.FragmentUserBinding
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.User
import java.text.SimpleDateFormat
import java.util.*

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val buttonLogout = binding.buttonLogout
        buttonLogout.setOnClickListener {
            logoutUser()
        }

        val buttonEditUser = binding.buttonEditUser
        buttonEditUser.setOnClickListener{
            goToEditUser()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        user = GlobalInfo.user
        user?.let { showUserData(it) }
    }

    fun logoutUser(){
        GlobalInfo.user = null
        val intent = Intent(requireContext(), Login::class.java)
        startActivity(
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        )
    }

    fun goToEditUser(){
        val intent = Intent(requireContext(), UserForm::class.java)
        startActivity(intent)
    }

    fun showUserData(user: User) {

        val textUserEmail: TextView = binding.textUserEmail
        val textUserType: TextView = binding.textUserType
        val textUserDNI: TextView = binding.textUserDNI
        val textUserFullName: TextView = binding.textUserFullName
        val textUserName: TextView = binding.textUserName
        val textUserFatherLastName: TextView = binding.textUserFatherLastName
        val textUserMotherLastName: TextView = binding.textUserMotherLastName
        val textUserBirthdate: TextView = binding.textUserBirthdate

        textUserFullName.text = user.name + " " + user.father_last_name + " " + user.mother_last_name
        textUserType.text = if(user.type == "customer") {"Cliente"} else {"Administrador"}
        println(textUserType.text)
        textUserEmail.text = user.email
        textUserDNI.text = user.dni
        textUserName.text = user.name
        textUserFatherLastName.text = user.father_last_name
        textUserMotherLastName.text = user.mother_last_name

        val pattern = "yyyy-MM-dd "
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(user.birthday)
        textUserBirthdate.text = date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}