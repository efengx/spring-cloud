package com.ofengx.cloudserviceupload.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class UploadTest extends GroovyTestCase {

    @Autowired
    private MockMvc mvc

    @Test
    void testUploadFile() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                'file',
                'test11.csv',
                'text/plain',
                'Spring Framwork'.getBytes())


        this.mvc.perform(
                fileUpload('/upload')
                .file(multipartFile))
                .andExpect(status().is(200))
                .andExpect(content().string('success'))
    }

    @Test
    void testUploadFiles() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                'files',
                'test1.csv',
                'text/plain',
                'Spring Framwork1'.getBytes())

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
                fileUpload('/upload')
                        .file(multipartFile)
                        .file(multipartFile2)
                        .file(multipartFile3))
                .andExpect(status().is(200))
                .andExpect(content().string('success'))
    }
}
