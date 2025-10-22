package me.juanfelipecaro.unabshop

import android.util.Patterns

// 🔹 Validación de correo electrónico
fun validateEmail(email: String): Pair<Boolean, String> {
    return when {
        email.isEmpty() -> Pair(false, "El email no puede estar vacío")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "Ese email no es válido")
        else -> Pair(true, "")
    }
}

// 🔹 Validación de contraseña
fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "La contraseña es requerida")
        password.length < 8 -> Pair(false, "Debe tener al menos 8 caracteres")
        !password.any { it.isDigit() } -> Pair(false, "Debe tener al menos un número")
        else -> Pair(true, "")
    }
}

// 🔹 Validación de nombre
fun validateName(name: String): Pair<Boolean, String> {
    return when {
        name.isEmpty() -> Pair(false, "El nombre es requerido")
        name.length < 3 -> Pair(false, "Debe tener al menos 3 caracteres")
        else -> Pair(true, "")
    }
}

// 🔹 Validación de confirmación de contraseña
fun validateConfirmPassword(password: String, confirmPassword: String): Pair<Boolean, String> {
    return when {
        confirmPassword.isEmpty() -> Pair(false, "Confirma tu contraseña")
        confirmPassword != password -> Pair(false, "Las contraseñas no coinciden")
        else -> Pair(true, "")
    }
}