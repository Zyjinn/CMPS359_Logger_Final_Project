package com.example.cmps359_logger_final_project

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import kotlinx.android.synthetic.*
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

//        Variables for the timer
        val timer = getView()?.findViewById<Chronometer>(R.id.c_meter)
        var isWorking = false
        var stopTime = 0;

//        Get buttons
        var startButton = getView()?.findViewById<Button>(R.id.timerStartBtn)
        var splitButton = getView()?.findViewById<Button>(R.id.timerSplitBtn)
        var stopButton = getView()?.findViewById<Button>(R.id.timerStopBtn)
        var resetButton = getView()?.findViewById<Button>(R.id.timerResetBtn)

//        Start btn listener to start the timer
        timerStartBtn.setOnClickListener{
            // start the timer
            timer?(SystemClock.elapsedRealtime() + stopTime)
            timer?.start()

//            Hide the start button and reveal the stop button
            startButton?.visibility = View.GONE
            stopButton?.visibility = View.GONE

        }
        timerStopBtn.setOnClickListener{
            // stop the timer
            timer?.stop()

//            Hide the stop button and reveal the start button
            startButton?.visibility = View.VISIBLE
            stopButton?.visibility = View.GONE
        }

        timerResetBtn.setOnClickListener{
            stopTime = 0;
            timer?.stop()
            startButton?.visibility = View.GONE
            stopButton?.visibility = View.GONE

        }
    }

    override fun onStart() {
        super.onStart()

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