package org.example.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilka_krosovok.R
import com.example.mobilka_krosovok.ui.theme.MobilkakrosovokTheme
import java.util.regex.Pattern

@Composable
fun RegistrationScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: (name: String, email: String, password: String) -> Unit = { _, _, _ -> }
) {
    RegistrationContent(onLoginClick, onRegisterClick)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegistrationContent(
    onLoginClick: () -> Unit = {},
    onRegisterClick: (name: String, email: String, password: String) -> Unit = { _, _, _ -> }
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var privacyAccepted by remember { mutableStateOf(false) }

    val isEmailValid by derivedStateOf { isValidEmail(email) }
    val isPasswordValid by derivedStateOf { isValidPassword(password) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = LocalContext.current.getString(R.string.registration),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = LocalContext.current.getString(R.string.fill_data_or_social),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        RegistrationTextField(
            value = name,
            onValueChange = { name = it },
            label = LocalContext.current.getString(R.string.your_name),
            placeholder = "xxxxxxxxx"
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            RegistrationTextField(
                value = email,
                onValueChange = { email = it },
                label = LocalContext.current.getString(R.string.email),
                placeholder = "xyz@gmail.com"
            )
            if (email.isNotEmpty() && !isEmailValid) {
                Text(
                    text = "Invalid email format. Example: name@domain.ru",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            RegistrationTextField(
                value = password,
                onValueChange = { password = it },
                label = LocalContext.current.getString(R.string.password),
                placeholder = "**********",
                visualTransformation = PasswordVisualTransformation()
            )
            if (password.isNotEmpty() && !isPasswordValid) {
                Text(
                    text = "Password must be at least 8 characters with 1 uppercase, 1 digit, and 1 special character (!@#$%^&*)",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = privacyAccepted,
                onCheckedChange = { privacyAccepted = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline
                )
            )
            Text(
                text = LocalContext.current.getString(R.string.privacy_agreement),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = { onRegisterClick(name, email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            enabled = privacyAccepted && name.isNotEmpty() && isEmailValid && isPasswordValid
        ) {
            Text(text = LocalContext.current.getString(R.string.register))
        }

        Text(
            text = LocalContext.current.getString(R.string.already_have_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onLoginClick() }
        )
    }
}

// Email validation function
fun isValidEmail(email: String): Boolean {
    val pattern = Pattern.compile("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}\$")
    return pattern.matcher(email).matches()
}

// Password validation function
fun isValidPassword(password: String): Boolean {
    if (password.length < 8) return false

    val hasUppercase = password.any { it.isUpperCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { it in "!@#$%^&*" }

    return hasUppercase && hasDigit && hasSpecialChar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            visualTransformation = visualTransformation
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interaction,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    MobilkakrosovokTheme {
        RegistrationScreen()
    }
}