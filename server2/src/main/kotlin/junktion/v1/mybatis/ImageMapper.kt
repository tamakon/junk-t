package junktion.v1.mybatis

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
interface ImageMapper {
	@Insert("insert into images(name, created_at, updated_at) values(#{name}, #{createdAt}, #{updatedAt})")
	fun save(image: ImageRecord)

	@Select("select name, created_at, updated_at from images")
	fun selectAll(): List<ImageRecord>
}