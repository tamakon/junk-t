package com.junktion.api.v1.repositories

import org.springframework.stereotype.Repository

interface ImageRepository {
    fun register()
}

@Repository
class ImageRepositoryMybatis: ImageRepository {
    override fun register() {
        TODO("not implemented")
    }
}