package com.example.cmps359_logger_final_project

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_timer.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    Variable declarations
    var timer: Chronometer? = null
    var startButton: Button? = null
    var resetButton: Button? = null
    var stopButton: Button? = null
    var splitButton: Button? = null
    var splitTimes: TextView? = null
    var stopTime: Long? = 0
    var splitTimer: Long? = 0
    var previousTime: Long? = 0


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
        return inflater.inflate(R.layout.fragment_timer, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        }


    override fun onStart() {
        super.onStart()
        //        Variables for the timer
        timer = getView()?.findViewById<Chronometer>(R.id.c_meter)



//        Get buttons
        startButton = getView()?.findViewById<Button>(R.id.timerStartBtn)
        splitButton = getView()?.findViewById<Button>(R.id.timerSplitBtn)
        resetButton = getView()?.findViewById<Button>(R.id.timerResetBtn)
        splitTimes = getView()?.findViewById<TextView>(R.id.splitsList)
        splitTimer = timer?.getBase()

//        Split button is hidden to start
        splitButton?.visibility = View.GONE

//        Start btn listener to start the timer
        timerStartBtn.setOnClickListener {

            // start the timer
            timer?.setBase((SystemClock.elapsedRealtime() + stopTime!!))
            timer?.start()

//            Hide the start button and reveal the stop button
            startButton?.visibility = View.GONE
            splitButton?.visibility = View.VISIBLE
            splitTimer = timer?.getBase()
            previousTime = 0

        }


        timerResetBtn.setOnClickListener {
            timer?.setBase(SystemClock.elapsedRealtime())
            stopTime = 0;
            timer?.stop()
            startButton?.visibility = View.VISIBLE
            splitButton?.visibility = View.GONE
        }
        timerSplitBtn.setOnClickListener {
//            var currentSplit: Long? = splitTimer?.minus(SystemClock.elapsedRealtime())
            var currentSplit: Long? = SystemClock.elapsedRealtime() - splitTimer!!
            // display and store the current split
            currentSplit = currentSplit?.minus(previousTime!!) // get rid of prev. splits

//            Convert
            var currentSplitSecs: Long? = currentSplit?.div(1000)?.rem(60) // convert to seconds
            var currentSplitMins: Long? = currentSplit?.div(1000 * 60)?.rem(60)  // get minutes
            var currentSplitHrs: Long? = currentSplit?.div(1000 * 60 * 60)?.rem(24) // get hours

//            Print out the current split
            splitTimes?.text = "$currentSplitSecs secs : $currentSplitMins mins : $currentSplitHrs hrs"
            println(currentSplit)

//            make it so all previous time is removed, set back to 0?
//            Get the current time - previous times
            previousTime = previousTime?.plus(currentSplit!!)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}