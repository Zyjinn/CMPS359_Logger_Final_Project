package com.example.cmps359_logger_final_project
import android.app.Application
import android.os.AsyncTask
import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData

class TimesRepository(application: Application) {

    private var timesDao: TimesDao?
    init {
        val db: TimesRoomDatabase? = TimesRoomDatabase.getDatabase(application)
        timesDao = db?.timesDao()
    }

//    Call the async tasks
    fun getTimes(id: Int){
        val task = QueryAsyncTask(timesDao)
        task.execute(id.toString())
}
    fun insertTime(time: Times){
        val task = InsertAsyncTask(timesDao)
        task.execute(time)
    }

    fun updateTimes(time: Long, id: Int, name: String){
        val task = UpdateAsyncTask(timesDao)
        val taskParameters = MyTaskParams(time, id, name)
        task.execute(taskParameters)
    }

    val searchResults = MutableLiveData<List<Times>>()

    private class MyTaskParams internal constructor(var time: Long, var id: Int, var username: String)



    fun asyncFinished(results: List<Times>) {
        searchResults.value = results
    }

    private class QueryAsyncTask constructor(val asyncTaskDao: TimesDao?): AsyncTask<String, Void, List<Times>>() {
        var delegate: TimesRepository? = null

        override fun doInBackground(vararg params: String): List<Times>? {
            return asyncTaskDao?.getTimes(Integer.parseInt(params[0]))
        }

        override fun onPostExecute(result: List<Times>) {
            delegate?.asyncFinished(result)
        }
    }
    private class InsertAsyncTask constructor(private val asyncTaskDao: TimesDao?) :
        AsyncTask<Times, Void, Void>() {
        override fun doInBackground(vararg params: Times): Void? {
            asyncTaskDao?.insertTime(params[0])
            return null
        }
    }
    private class UpdateAsyncTask constructor(val asyncTaskDao: TimesDao?):
        AsyncTask<MyTaskParams, Void, Unit>(){
        override fun doInBackground(vararg params: MyTaskParams?): Unit? {
            return asyncTaskDao?.updateTimes(params[0]!!.time, params[0]!!.id, params[0]!!.username)
        }
    }
}