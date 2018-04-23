package com.ofengx.cloudserviceupload.controller

import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest

@RestController
class Upload {
    private final static Logger logger = LoggerFactory.getLogger(Upload.class)
    private final static String SUCCESS = "success"

    @Autowired
    private HttpServletRequest request

    @PostMapping('/upload')
    String upload(
            @RequestParam(value = 'file', required = false) MultipartFile file
            , @RequestParam(value = 'files', required = false) List<MultipartFile> files
    ) {
        System.out.println(ResourceUtils.getURL('classpath:').getPath())

        if (file && !file.isEmpty()) {
            return uploadFile(file)
        } else {
            if (files && files.size() > 0) {
                return uploadFiles(files)
            } else {
                return 'file is empty'
            }
        }
    }

//    Single file upload
    String uploadFile(MultipartFile file) {
        System.out.println('uploadFile')
        try {
            logger.info(
                    'filename: {}; originalFilename: {}; content: {}; '
                    , file.name
                    , file.originalFilename
                    , IOUtils.toString(file.inputStream, 'UTF-8')
            )
        } catch (FileNotFoundException e) {
            e.printStackTrace()
            return 'file is not found'
        } catch (IOException e) {
            e.printStackTrace()
            return 'file upload failed'
        }
        return SUCCESS
    }

//    Multipart file upload
    String uploadFiles(List<MultipartFile> files) {
        MultipartFile file = null
        BufferedOutputStream stream = null
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
