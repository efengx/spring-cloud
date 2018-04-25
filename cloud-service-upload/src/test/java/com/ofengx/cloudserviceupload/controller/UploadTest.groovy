package com.ofengx.cloudserviceupload.controller

import org.apache.commons.io.IOUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class UploadTest extends GroovyTestCase {

    @Autowired
    private MockMvc mvc

    @Value('classpath:static/test.csv')
    private Resource test

    @Test
    void testUploadFiles() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                'files',
                'test1.csv',
                'text/plain',
                IOUtils.toString(test.inputStream).getBytes()
        )

        MockMultipartFile multipartFile2 = new MockMultipartFile(
                'files',
                'test2.csv',
                'text/plain',
                'Spring Framwork2'.getBytes())

        MockMultipartFile multipartFile3 = new MockMultipartFile(
                'files',
                'test3.csv',
                'text/plain',
                'Spring Framwork3'.getBytes())

        this.mvc.perform(
                multipart('/upload')
                        .file(multipartFile)
                        .file(multipartFile2)
                        .file(multipartFile3))
                .andExpect(status().is(200))
                .andExpect(content().string('success'))
    }
}
