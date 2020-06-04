package com.terentiev.recipeplanner.ui.calendar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.terentiev.recipeplanner.R
import com.terentiev.recipeplanner.RecipePlanner
import com.terentiev.recipeplanner.ui.login.LoginActivity
import com.terentiev.recipeplanner.ui.login.LoginViewModel
import com.terentiev.recipeplanner.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    private lateinit var calendarViewModel: CalendarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val textView: TextView = root.findViewById(R.id.text_calendar)
        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sign_out.setOnClickListener {
            RecipePlanner.instance.setUserToken("")
            RecipePlanner.instance.setUsername("")
            val i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
            activity?.finish()
        }
    }
}
