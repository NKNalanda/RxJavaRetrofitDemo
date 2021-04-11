package com.example.rxjavaretrofitdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rxjavaretrofitdemo.model.MovieDBResponse
import com.example.rxjavaretrofitdemo.model.MovieRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(var appln: Application) : AndroidViewModel(appln) {
    var disposable: CompositeDisposable = CompositeDisposable()
    var repository: MovieRepository = MovieRepository(appln, disposable)
    var popularLiveData: MutableLiveData<MovieDBResponse> = MutableLiveData()

    fun getPopularMovies() {
        repository.getPopularMoviesLiveData(popularLiveData)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}