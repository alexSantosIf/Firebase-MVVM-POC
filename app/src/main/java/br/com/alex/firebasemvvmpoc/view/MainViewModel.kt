package br.com.alex.firebasemvvmpoc.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alex.firebasemvvmpoc.data.model.DataModel
import br.com.alex.firebasemvvmpoc.data.service.MainService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel: ViewModel(), KoinComponent {

    private val mainService: MainService by inject()

    var mainLiveData = MutableLiveData<DataModel>()

    fun login(email: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val authStatus: SignInMethodQueryResult? = mainService.authenticateUser(email)
            if (authStatus?.signInMethods.isNullOrEmpty()) {
                mainLiveData.postValue(DataModel(status = "error", error = "Ocorreu um erro ao logar"))
            } else {
                callUserLogin(email)
            }
        }
    }

    private fun callUserLogin(email: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val signInStatus: AuthResult? = mainService.signInOnFirebase(email, "654321")
            if (signInStatus?.user?.isEmailVerified == true) {
                mainLiveData.postValue(DataModel(status = "error", error = "Ocorreu um erro ao logar"))
            } else {
                mainLiveData.postValue(DataModel(status = "success", data = signInStatus))
            }
        }
    }
}