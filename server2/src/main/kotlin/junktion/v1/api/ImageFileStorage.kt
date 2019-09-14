package junktion.v1.api

import junktion.v1.core.Image

interface ImageFileStorage {
	/**
	 * @throws InfrastructureException ファイル処理に失敗した場合。
	 * 	原因はスタックトレースを参照するようにしてください。
	 */
	fun save(image: Image)
}