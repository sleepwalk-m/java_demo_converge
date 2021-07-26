package com.mask.ssm.fastdfs.config;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: Mask.m
 * @create: 2021/06/27 15:49
 * @description: 工具类
 */
@Component
public class FastDFSClientUtil {

    @Autowired //注入storage客户端
    private FastFileStorageClient storageClient;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile((InputStream) file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();// 返回的是fileId（路径）
    }


    /**
     * 删除文件，根据fileId（路径）删除
     *
     * @param fileId
     */
    public void delFile(String fileId){
        storageClient.deleteFile(fileId);
    }

    /**
     * 下载
     * @param groupName
     * @param path
     * @return
     */
    public byte[] download(String groupName, String path) throws IOException {

        // 回调方法，返回流
        InputStream ins = storageClient.downloadFile(groupName, path, new DownloadCallback<InputStream>() {
            @Override
            public InputStream recv(InputStream ins) throws IOException {
                // 将此ins返回给上面的ins
                return ins;
            }
        });

        // 将流转换为字节数组返回
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = ins.read(buff, 0, 100)) > 0) {
            byteArrayOutputStream.write(buff, 0, rc);
        }
        return byteArrayOutputStream.toByteArray();
    }

}
