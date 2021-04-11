package com.example.rxjavaretrofitdemo.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rxjavaretrofitdemo.R
import com.example.rxjavaretrofitdemo.service.MovieDBService
import com.example.rxjavaretrofitdemo.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MovieRepository(val appln: Application, var disposable: CompositeDisposable) {

    fun getPopularMoviesLiveData(popularLiveData: MutableLiveData<MovieDBResponse>) {
        var movieDBService: MovieDBService = RetrofitInstance.getService()
        var observable: Observable<MovieDBResponse> = movieDBService.getPopularMovies(
                appln.applicationContext.getString(R.string.api_key)
        )

        disposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    Observable.fromArray(it.movies)
                }
                .subscribeWith(object : DisposableObserver<List<Movie>>() {
                    override fun onNext(t: List<Movie>) {
                        Log.e(TAG, t.size.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.toString())
                    }

                    override fun onComplete() {
                        Log.e(TAG, "completed")
                    }

                }))
    }

    companion object {
        private const val TAG = "MovieRepository"
    }
}