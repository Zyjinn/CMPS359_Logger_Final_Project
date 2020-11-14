package com.example.cmps359_logger_final_project

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Ocarina_of_Time.newInstance] factory method to
 * create an instance of this fragment.
 */
class Ocarina_of_Time : Fragment() {
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
        return inflater.inflate(R.layout.fragment_ocarina_of__time, container, false)
    }

    override fun onStart() {
        super.onStart()

//        Get the splits table var
        var tableLayout = view?.findViewById<TableLayout>(R.id.splitsTable)

//        Query the DB to get user times
        try {
            var rank = 1
            val curse = db!!.rawQuery(
                "SELECT * FROM times WHERE gameId = 2 ORDER BY totalTime"
                , null
            )
            val cindexname = curse.getColumnIndex("username")
            val cindextime = curse.getColumnIndex("totalTime")
            curse.moveToFirst()
            var message = "No match!"


//            Create the table header rows
            val tbrow0 = TableRow(context)
            val tv0 = TextView(context)
            tv0.text = " Rank "
            tv0.setTextColor(Color.BLACK)
            tv0.gravity = Gravity.CENTER
            tbrow0.addView(tv0)
            val tv1 = TextView(context)
            tv1.text = " User "
            tv1.setTextColor(Color.BLACK)
            tv1.gravity = Gravity.CENTER
            tbrow0.addView(tv1)
            val tv2 = TextView(context)
            tv2.text = " Time "
            tv2.setTextColor(Color.BLACK)
            tv2.gravity = Gravity.CENTER
            tbrow0.addView(tv2)
            tableLayout?.addView(tbrow0)




//            populate the table first time
            if (curse.count > 0) {
                do {
                    var time = curse.getLong(cindextime)
                    var timeSecs = time.div(1000).rem(60) // convert to seconds
                    var timeMins = time.div(1000 * 60).rem(60)  // get minutes
                    var timeHrs = time.div(1000 * 60 * 60).rem(24) // get hours



                    val tbrow = TableRow(context)
                    val t1v = TextView(context)
                    t1v.text = rank.toString()
                    t1v.setTextColor(Color.BLACK)
                    t1v.gravity = Gravity.CENTER
                    tbrow.addView(t1v)

                    val t2v = TextView(context)
                    t2v.text = curse.getString(cindexname)
                    t2v.setTextColor(Color.BLACK)
                    t2v.gravity = Gravity.CENTER
                    tbrow.addView(t2v)

                    val t3v = TextView(context)
                    t3v.text = "[$timeHrs.$timeMins.$timeSecs]"
                    t3v.setTextColor(Color.BLACK)
                    t3v.gravity = Gravity.CENTER
                    tbrow.addView(t3v)

                    tableLayout?.addView(tbrow)
                    rank += 1

//                    Continue to do so till we run out of data
                } while (curse.moveToNext())
            }
//            Catch and print exceptions to the console
        } catch (e: Exception) {
            System.out.println(e.message)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Ocarina_of_Time.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Ocarina_of_Time().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}