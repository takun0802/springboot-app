# -------------------------------------------------------------------------------
# Stage 1: ビルドステージ - アプリケーションのビルドとJARファイルの作成
# -------------------------------------------------------------------------------
# MavenとJava 17 (Eclipse Temurin) を含むベースイメージを使用
FROM maven:3.8.7-eclipse-temurin-17 AS builder

# 作業ディレクトリを設定
WORKDIR /app

# Mavenの依存関係をキャッシュするためにpom.xmlのみを先にコピー
COPY pom.xml .

# 依存関係をダウンロード (pom.xmlが変更されない限りキャッシュされる)
RUN mvn dependency:go-offline -B

# プロジェクトのソースコードをコピー
COPY src ./src

# Mavenを使用してアプリケーションをパッケージング (JARファイルを生成)
RUN mvn package -DskipTests

# -------------------------------------------------------------------------------
# Stage 2: 実行ステージ - ビルドされたJARファイルを実行するための軽量なJREイメージ
# -------------------------------------------------------------------------------
# Java 21 JRE (Eclipse Temurin) の軽量イメージを使用
FROM eclipse-temurin:21-jre-jammy # 環境に合わせて eclipse-temurin:21-jre-alpine なども選択可能

# 作業ディレクトリを設定
WORKDIR /app

# ビルドステージで作成されたJARファイルをコピー
COPY --from=builder /app/target/*.jar app.jar

# アプリケーションがリッスンするポートを公開 (Spring Bootのデフォルトは8080)
EXPOSE 8080

# アプリケーションを実行するコマンド
CMD ["java", "-jar", "app.jar"]