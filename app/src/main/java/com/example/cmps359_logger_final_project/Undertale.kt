package com.example.cmps359_logger_final_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_undertale.*
import com.example.cmps359_logger_final_project.TimesRepository
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Application


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Undertale.newInstance] factory method to
 * create an instance of this fragment.
 */
class Undertale : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_undertale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        //listUndertale.setOnClickListener {
            try {
                var rank = 1
                val curse = db!!.rawQuery("SELECT * FROM times WHERE gameId = 0 ORDER BY totalTime"
                    , null)
                val cindexname = curse.getColumnIndex("username")
                val cindextime = curse.getColumnIndex("totalTime")
                curse.moveToFirst()
                var message = "No match!"
                if (curse.count > 0) {
                    message = ""
                    do {
                        var time = curse.getLong(cindextime)
                        var timeSecs = time.div(1000).rem(60) // convert to seconds
                        var timeMins = time.div(1000 * 60).rem(60)  // get minutes
                        var timeHrs= time.div(1000 * 60 * 60).rem(24) // get hours
                        message = message + "$rank (" + curse.getString(cindexname) +
                                "), Time: [$timeHrs HRS $timeMins MINS $timeSecs SECS]\n"
                        rank +=1
                    } while (curse.moveToNext())
                    listTimes!!.text = message
                }
            } catch (e: Exception) {
                listTimes!!.text = e.toString()
            }
       // }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Undertale.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Undertale().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}