version: 0.2

env:
  secrets-manager:
    DOCKERHUB_USERNAME: "docker-hub-credentials:username"
    DOCKERHUB_PASSWORD: "docker-hub-credentials:password"

phases:
  pre_build:
    commands:
      # AWS ECRへのログイン
      - aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 513594376299.dkr.ecr.ap-northeast-1.amazonaws.com
      # Docker Hubへのログイン
      - echo "$DOCKERHUB_PASSWORD" | docker login --username "$DOCKERHUB_USERNAME" --password-stdin

  build:
    commands:
      # アプリケーションのビルドとDockerイメージのビルドを同時に行う
      # ローカルでのビルドイメージ名を 'my-app' に固定します。
      # これはCodeBuildの環境変数$ImageNameに依存しません。
      - echo "Building the Docker image using multi-stage build..."
      - docker build -t my-app -f ./Dockerfile . # ローカルイメージ名を $ImageName から 'my-app' に変更

  post_build:
    commands:
      # ECRにタグ付けしてプッシュ
      # ローカルでビルドした 'my-app:latest' イメージに、
      # 'springboot-app' というECRリポジトリ名をタグ付けします。
      - docker tag my-app:latest 513594376299.dkr.ecr.ap-northeast-1.amazonaws.com/springboot-app:latest
      # 'springboot-app' リポジトリにプッシュします。
      - docker push 513594376299.dkr.ecr.ap-northeast-1.amazonaws.com/springboot-app:latest

artifacts:
  files:
    - '**/*'