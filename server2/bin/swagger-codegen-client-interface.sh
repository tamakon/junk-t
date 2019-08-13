#!/usr/bin/env bash

# クライアントアプリケーション用のリクエスト/レスポンスインターフェースを生成する。
swagger-codegen generate \
-D models \
-i http://localhost:8080/v2/api-docs \
-l typescript-angular \
-o server2/client-interfaces