package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                AgeCheckerScreen()
            }
        }
    }
}

@Composable
fun AgeCheckerScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Box giúp căn giữa nội dung trên màn hình
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0)), // Màu nền nhẹ nhàng
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f) // Chiếm 90% chiều rộng màn hình
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "THỰC HÀNH 01",
                    fontSize = 22.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Họ và tên") },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Tên") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it.filter { char -> char.isDigit() } },
                    label = { Text("Tuổi") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        result = when (age.toIntOrNull()) {
                            null -> "Vui lòng nhập tuổi hợp lệ"
                            in 0..2 -> "Em bé"
                            in 3..6 -> "Trẻ em"
                            in 7..65 -> "Người lớn"
                            in 66..120 -> "Người già"
                            else -> "Tuổi không thực tế"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF))
                ) {
                    Text("Kiểm tra", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (result.isNotEmpty()) {
                    Text(
                        text = result,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgeCheckerScreenPreview() {
    MyApplicationTheme {
        AgeCheckerScreen()
    }
}
