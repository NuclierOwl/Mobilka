package org.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mobilka_krosovok.R

@Composable
fun SignInScreen() {
    SignInContent()
}


@Composable
fun SignInContent() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        TitleWithSubtitleText(
            title = context.getString(R.string.Privetstvie),
            subtitle = context.getString(R.string.fill_data_or_social)
        )
        Avtorizator(
            value = email,
            onChegeVal = { email = it },
            labelText = context.getString(R.string.email)
        )
        Avtorizator(
            value = password,
            onChegeVal = { password = it },
            labelText = context.getString(R.string.password),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}

@Composable
fun TitleWithSubtitleText(title: String, subtitle: String) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Avtorizator(
    value: String,
    onChegeVal: (String) -> Unit,
    labelText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        if (labelText != null) {
            Text(
                text = labelText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = value,
            onValueChange = onChegeVal,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "xyz@gmail.com",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                }
                innerTextField()
            }
        )
    }
}