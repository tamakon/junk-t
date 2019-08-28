package com.junktion.api.v1.repositories

import com.junktion.api.v1.models.image.Image
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface ImageMapper {
	@Insert("insert into images(tag, created_at, updated_at) values(#{tag}, #{createdAt}, #{updatedAt})")
	fun save(image: Image)

	@Select("select tag, created_at, updated_at from images")
	fun selectAll(): List<Image>
}