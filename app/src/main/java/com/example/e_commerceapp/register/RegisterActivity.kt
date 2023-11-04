package com.example.e_commerceapp.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.e_commerceapp.MainActivity
import com.example.e_commerceapp.R
import com.example.e_commerceapp.login.LoginActivity
import com.example.e_commerceapp.register.ui.theme.E_commerceAppTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class RegisterActivity : ComponentActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E_commerceAppTheme {
                // A surface container using the 'background' color from the theme

                registerContent(navigator = this)
            }
        }
    }

    override fun navigateToHome() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToLoginScreen() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registerContent(
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigator: Navigator
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.mainColor))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.route),
                    contentDescription = "route",
                    modifier = Modifier
                        .fillMaxHeight(0.2F)
                        .fillMaxWidth(0.5f)

                )
            }
            AuthTextField(
                state = viewModel.firstNameState,
                lable = "Full name",
                errorState = viewModel.firstNameError
            )
            AuthTextField(
                state = viewModel.phoneNumberState,
                lable = "Mobile Number",
                errorState = viewModel.phoneNumberError
            )
            AuthTextField(
                state = viewModel.emailState,
                lable = "E-Mail address",
                errorState = viewModel.emailError
            )
            AuthTextField(
                state = viewModel.passwordState,
                lable = "Password",
                errorState = viewModel.passwordError
            )
            Spacer(modifier = Modifier.fillMaxSize(0.3F))
            buttonRegister("Sign Up") {
                viewModel.sendAuthDataToFirebase()
            }

            TextButton(onClick = {
                viewModel.navigateToLoginScreen()
            }) {
                Text(
                    text = "Have an account? Login Now", style = TextStyle(
                        color = colorResource(id = R.color.white),
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            loadingDialog()
            alertDialog(navigator = navigator)
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    state: MutableState<String>,
    lable: String,
    errorState: MutableState<String>,
    isPassword: Boolean = false
) {

    Text(
        text = lable, style = TextStyle(
            color = colorResource(id = R.color.white),
            fontSize = 18.sp
        ),
        modifier = Modifier.padding(start = 19.dp)
    )
    TextField(
        value = state.value,
        onValueChange = { newValue ->
            state.value = newValue
        },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        label = {
            Text(
                text = lable,
                style = TextStyle(
                    color = colorResource(id = R.color.gray),
                    fontSize = 13.sp
                ),
            )
        },

        isError = errorState.value.isNotEmpty(),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
    )
    if (errorState.value.isNotEmpty()) {
        Text(
            text = errorState.value,
            style = TextStyle(color = Color.Red),
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 2.dp)
        )
    }

}

@Composable
fun buttonRegister(
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = {
            onButtonClick()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(
                id = R.color.white
            ),
            contentColor = colorResource(id = R.color.mainColor)
        ),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = buttonText,
            style = TextStyle(color = colorResource(id = R.color.mainColor), fontSize = 24.sp)
        )

    }

}

@Composable
fun loadingDialog(viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    if (viewModel.showLoading.value) {
        Dialog(onDismissRequest = {}) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                CircularProgressIndicator(color = colorResource(id = R.color.mainColor))
            }

        }
    }

}

@Composable
fun alertDialog(
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigator: Navigator
) {
    viewModel.navigator = navigator
    if (viewModel.message.value.isNotEmpty()) {
        AlertDialog(onDismissRequest = {
            viewModel.message.value = ""

        },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.message.value = ""
                    viewModel.navigator?.navigateToHome()
                }) {
                    Text(text = "Ok")
                }
            },
            text = {
                Text(text = viewModel.message.value)
            })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    registerContent(navigator = object : Navigator {
        override fun navigateToHome() {

        }

        override fun navigateToLoginScreen() {

        }
    })
}