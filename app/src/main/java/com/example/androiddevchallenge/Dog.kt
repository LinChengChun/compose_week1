package com.example.androiddevchallenge

import java.util.*

data class Dog(
    var id: String = UUID.randomUUID().toString(),
    var url: String = "https://tse2-mm.cn.bing.net/th/id/OIP.Bd6fsETCLM_Jd4d58UYvywHaEo?pid=ImgDet&rs=1",
    var name: String = "斗牛犬",
    var type: Int = 0,
    var year: Int = 2
)
