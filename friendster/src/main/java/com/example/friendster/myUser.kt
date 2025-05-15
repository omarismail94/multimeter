package com.example.friendster

import com.example.lib.SecretSauce

fun myUser(): Boolean {
    SecretSauce().onlyForFriends()
    return true
}