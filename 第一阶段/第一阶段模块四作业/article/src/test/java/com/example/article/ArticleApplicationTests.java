package com.example.article;

import com.lagou.mapper.NoticeMapper;
import com.lagou.pojo.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleApplicationTests {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    void contextLoads() {
        List<Notice> notices = noticeMapper.selectNoticeAll();
        for (Notice notice : notices) {
            System.out.println(notice);
        }
    }

}
