# ローカル環境
起動方法
```bash
cd server2
./gradlew bootRun --args='--spring.profiles.active=dev'
```
停止方法
```bash
Ctrl+C
```

# ローカル環境(Docker)
起動方法
```bash
cd junk-t
docker-sync start
docker-compose up --detach --build
```
停止方法
```bash
docker-compose down
docker-sync stop 
```
ログ参照方法
```bash
docker-compose logs --follow
```
