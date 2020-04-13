package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> queryAccountList() throws Exception {
        return resumeDao.findBySql();
    }

    /**
     * 根据id删除实体
     * @param id
     */
    @Override
    public void deleteResume(Long id) {
        resumeDao.deleteByResumeId(id);
    }

    /**
     * 新增实体
     * @param resume
     */
    @Override
    public void insertResume(Resume resume){
        resumeDao.save(resume);
    }

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @Override
    public Optional<Resume> selectResumeInfo(Long id) {
        return resumeDao.findById(id);
    }

    /**
     * 根据id修改详情
     * @param resume
     */
    @Override
    public void updateResume(Resume resume) {
        resumeDao.save(resume);
    }
}
