package com.ggn.myapplication.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CircularLoading(show: Boolean) {
    if (show) {
        CircularProgressIndicator()
    }
}


@Composable
fun ErrorTextView(str: Any?, fontSize : TextUnit = 9.sp, modifier: Modifier = Modifier
    .wrapContentHeight()
    .wrapContentWidth()) {
    val stringError = str?.let {
        when (str) {
            is String -> {
                str.toString()
            }
            is Int    -> {
                stringResource(str)
            }
            else      -> {
                ""
            }
        }
    } ?: ""

    Text(
        modifier = modifier,
        text = stringError,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.error
        )
    )
}


@Composable
fun BasicButton(modifier: Modifier,txt:String, onClickAction:()->Unit)
{
    Button(
        onClick = onClickAction,
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
    ) {
        Text(text = txt)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginSignupTextField(value : String, hint: String, isSingleLine:Boolean,
                         visualTransformation: VisualTransformation = VisualTransformation.None,
                         updateKeyboardOptions: (KeyboardOptions) -> KeyboardOptions = {it},
                         modifier: Modifier, onValueChanged:(str:String)->Unit) {
    val currentKeyboard = LocalSoftwareKeyboardController.current

    var keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next,capitalization = KeyboardCapitalization.Sentences)
    keyboardOptions = updateKeyboardOptions(keyboardOptions)

    OutlinedTextField(
        modifier = modifier,
        singleLine = isSingleLine,
        label = { Text(text = hint) },
        value = value,
        keyboardActions = KeyboardActions(
            onDone = { currentKeyboard?.hide()}),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChanged
    )
}


@Composable
fun CustomDialog(
    title:String,
    text:String,
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = true,
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // TITLE
                Text(text = title, style = MaterialTheme.typography.titleSmall)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Button(onClick = onConfirmClicked, Modifier.fillMaxWidth().padding(20.dp)) {
                    Text(text = "OK")
                }
            }
        }
    }
}