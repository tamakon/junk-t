package junktion.v1.api

import junktion.v1.core.Image

interface ImageRepository {
	/**
	 * @throws InfrastructureException DB処理で何らかの問題が発生した場合。
	 * 	原因はスタックトレースを参照するようにしてください。
	 */
	fun register(image: Image)
}