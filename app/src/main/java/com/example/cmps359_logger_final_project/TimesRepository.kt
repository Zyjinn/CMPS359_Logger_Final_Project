package com.example.cmps359_logger_final_project
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class TimesRepository(application: Application) {

    val searchResults = MutableLiveData<List<Times>>()

    fun asyncFinished(results: List<Times>) {
        searchResults.value = results
    }

    private class QueryAsyncTask constructor(val asyncTaskDao: TimesDao?): AsyncTask<String, Void, List<Times>>() {
        var delegate: TimesRepository? = null

        override fun doInBackground(vararg params: Int): List<Times>? {
            return asyncTaskDao?.getTimes(params[0])
        }

        override fun onPostExecute(result: List<Times>) {
            delegate?.asyncFinished(result)
        }
    }
}