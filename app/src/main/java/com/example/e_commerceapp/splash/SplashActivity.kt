package com.example.e_commerceapp.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.e_commerceapp.MainActivity
import com.example.e_commerceapp.R
import com.example.e_commerceapp.login.LoginActivity
import com.example.e_commerceapp.ui.theme.E_commerceAppTheme

class SplashActivity : ComponentActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E_commerceAppTheme {
                // A surface container using the 'background' color from the theme
                splashContent(navigator = this@SplashActivity)

            }
        }
    }

//    override fun navigateToHomeScreen() {
//        val intent = Intent(this@SplashActivity, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
    override fun navigateToHomeScreen() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

@Composable
fun splashContent(
    viewModel: SplashViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigator: Navigator
) {
    viewModel.navigator = navigator
    viewModel.navigate()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Set the background image
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    E_commerceAppTheme {
        splashContent(navigator = object : Navigator {
            override fun navigateToHomeScreen() {
            }
            override fun navigateToLoginScreen() {
            }
        })

    }
}