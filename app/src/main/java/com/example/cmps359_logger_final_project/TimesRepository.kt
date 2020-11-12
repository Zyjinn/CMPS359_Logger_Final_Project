package com.example.cmps359_logger_final_project
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData

class TimesRepository(application: Application) {

    private class MyTaskParams internal constructor(var time: Long, var id: Int, var username: String)


    val searchResults = MutableLiveData<List<Times>>()

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
            AsyncTask<MyTaskParams, Void, Void>(){
        override fun doInBackground(vararg params: String?): Void {
            return asyncTaskDao?.updateTimes(params[0])
        }
    }
}