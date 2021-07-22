package br.com.alex.firebasemvvmpoc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.alex.firebasemvvmpoc.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLogin = findViewById(R.id.button_login)

        buttonLogin.setOnClickListener {
            mainViewModel.login("testemail@email.com")
            mainViewModel.mainLiveData.observe(this@MainActivity, Observer {
                when (it.status) {
                    "error" -> {
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                    "success" -> {
                        Toast.makeText(this@MainActivity, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            })
        }
    }
}