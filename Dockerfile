# ベースイメージ（Java 21 の公式イメージ）
FROM eclipse-temurin:21-jre

# 作業ディレクトリを作成
WORKDIR /app

# ローカルの JAR ファイルをコンテナにコピー
COPY target/awsMicroservice-0.0.1-SNAPSHOT.jar app.jar

# コンテナ起動時に実行するコマンド
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
