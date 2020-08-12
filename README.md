## 项目介绍

做这个小项目的主要目的是因为，经常要下载国外的一些资料，但是在国内下载会速度非常慢。

于是想通过香港等某些境外服务器，做中转下载。

粘贴要下载的原地址，然后点击Download按钮。能够得到一个中转下载的地址，可直接用迅雷等下载工具进行下载。

<img src="image/image-1.png"/>


## Docker 构建 （推荐）
docker pull pencilso/cloud-download:1.0

docker run -dit --name cloud-download -p 8080:8080 pencilso/cloud-download:1.0

## 非Docker运行
可以下载仓库中的cloud-download.jar 直接java -jar 命令运行即可