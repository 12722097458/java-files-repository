# 一、获取pem文件

下载**KeyManager**

`https://keymanager.org/`

创建测试证书，导出证书

两个文件:

* (1) .pem或.crt
* (2) .key

![image-20220605164054544](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220605164054.png)

![image-20220605164136557](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220605164136.png)

![image-20220605164223228](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220605164223.png)



# 二、将pem转换为P12

通过linux安装openssl

```shell
yum install openssl
```

上传**free-jack-cn-0605160826.crt**和**free-jack-cn-0605160826_key.key**两个文件。

执行脚本，并输入密码，生成SpringBoot支持的p12文件

```shell
openssl pkcs12 -export -in 证书名.pem -inkey 私钥名.key -out 改后的名.p12
openssl pkcs12 -export -in free-jack-cn-0605160826.crt -inkey free-jack-cn-0605160826_key.key -out out.p12
free-jack-cn-0605160826.crt  free-jack-cn-0605160826_key.key  out.p12
```

![image-20220605164938882](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220605164938.png)

**项目配置实现https协议**

```shell
server:
  ssl:
    key-store: C:\Users\ayinj\Desktop\fsdownload\out.p12
    key-store-password: 123456
    key-store-type: PKCS12
    enabled: true
```



# 三、其他

## 1、主流数字证书都有哪些格式？

>  https://help.aliyun.com/document_detail/42214.html?spm=5176.22414175.sslink.4.16762163UvwQjt

## 2、SSL证书普及

>  https://blog.freessl.cn/ssl-cert-format-introduce/