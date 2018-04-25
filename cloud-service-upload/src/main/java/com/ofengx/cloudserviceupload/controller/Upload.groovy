package com.ofengx.cloudserviceupload.controller

import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class Upload {
    private final static Logger logger = LoggerFactory.getLogger(Upload.class)
    private final static String SUCCESS = "success"

/**
 * @param file  : sing
 * @param files :
 * @param path  :
 */
    @PostMapping(
            value = '/upload'
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    String upload(
            @RequestPart('files') List<MultipartFile> files
    ) {
        System.out.println(ResourceUtils.getURL('classpath:').getPath())

        if (files && files.size() > 0) {
            return uploadFiles(files)
        } else {
            return 'files is empty'
        }
    }

//    Multipart file upload
    private String uploadFiles(List<MultipartFile> files) {
        files.stream().each{
            try {
                logger.info(
                        'filename: {}; originalFilename: {}; content: {}'
                        , it.name
                        , it.originalFilename
                        , IOUtils.toString(it.inputStream, 'UTF-8')
                )
            } catch (IOException e) {
                e.printStackTrace()
                return 'You failed to upload ' + it.getName()
            }
        }
        return SUCCESS
    }

}
