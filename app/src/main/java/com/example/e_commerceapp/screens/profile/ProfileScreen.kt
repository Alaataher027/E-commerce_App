package com.example.e_commerceapp.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_commerceapp.R
import com.example.e_commerceapp.login.Navigator
import com.example.e_commerceapp.login.contentLogin
import com.example.e_commerceapp.model.AppUser
import com.example.e_commerceapp.model.DataUtils
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun ProfileScreen(navController: NavHostController) {
    profileScreenContent()

}


@Composable
fun profileScreenContent(viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val userData = viewModel.userData.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.routeblue),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .padding(start = 2.dp)
        )
        Text(
            text = "Welcome,", style = TextStyle(
                color = colorResource(id = R.color.textColor),
                fontSize = 20.sp

            )
        )
        userData?.firstName?.let {
            Text(
                text = "it", style = TextStyle(
                    color = colorResource(id = R.color.textColor),
                    fontSize = 16.sp

                )
            )
        }


        DataUtils.firebaseUser?.email?.let {
            Text(
                text = it, style = TextStyle(
                    color = colorResource(id = R.color.textColor),
                    fontSize = 15.sp

                )
            )
        }


        Spacer(modifier = Modifier.fillMaxSize(0.04F))
        ProfileDataField(
            label = "Your full name",
            value = userData?.firstName.toString(),
            onValueChange = { newValue ->
                val updatedUserData = userData?.copy(firstName = newValue) ?: AppUser()
                viewModel.updateUserData(updatedUserData)
            }
        )
        ProfileDataField(
            label = "Your E-mail",
            value = userData?.email .toString(),
            onValueChange = { newValue ->
                val updatedUserData = userData?.copy(email = newValue) ?: AppUser()
                viewModel.updateUserData(updatedUserData)
            }
        )
//        ProfileDataField(
//            label = "Your password",
//            value = userData?.phoneNumber ?: "",
//            onValueChange = { newValue ->
//                val updatedUserData = userData?.copy(phoneNumber = newValue) ?: AppUser()
//                viewModel.updateUserData(updatedUserData)
//            }
//        )
        ProfileDataField(
            label = "Your mobile number",
            value = userData?.phoneNumber.toString(),
            onValueChange = { newValue ->
                val updatedUserData = userData?.copy(phoneNumber = newValue) ?: AppUser()
                viewModel.updateUserData(updatedUserData)
            }
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label, style = TextStyle(
                color = colorResource(id = R.color.textColor),
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = 2.dp)
        )

        TextField(
            value =value,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            onValueChange = onValueChange,
            //label = { Text(text = label) },
            //singleLine = true,
          //  keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
          //  keyboardActions = KeyboardActions(onDone = { /* Save the data */ }),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    colorResource(id = R.color.mainColor),
                    shape = RoundedCornerShape(8.dp)
                )
        )
        Spacer(modifier = Modifier.fillMaxSize(0.05F))
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    profileScreenContent()

}
