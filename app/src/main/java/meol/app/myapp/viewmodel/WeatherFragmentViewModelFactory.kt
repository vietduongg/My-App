package meol.app.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import meol.app.myapp.repository.WeatherRepository

class WeatherFragmentViewModelFactory(private val repository: WeatherRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherFragmentViewModel(repository) as T
    }
}