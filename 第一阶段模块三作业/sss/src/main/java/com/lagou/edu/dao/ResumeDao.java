package com.lagou.edu.dao;

import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResumeDao extends JpaRepository<Resume,Long>, JpaSpecificationExecutor<Resume> {

    /**
     * 查询全部
     * @return
     */
    @Query(value = "select * from tb_resume",nativeQuery = true)
    public List<Resume> findBySql();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from tb_resume where id = ?1",nativeQuery = true)
    void deleteByResumeId(Long id);
}
