package com.example.newsapp

data class Newsmodel (
    val title: String,
    val author: String,
    val url: String,
    val imageUrl: String
        )
{
    constructor()    : this("","","","")
}