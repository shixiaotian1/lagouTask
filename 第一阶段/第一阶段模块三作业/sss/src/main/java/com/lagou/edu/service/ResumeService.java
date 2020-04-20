package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {
    List<Resume> queryAccountList() throws Exception;

    void deleteResume(Long id);

    void insertResume(Resume resume);

    Optional<Resume> selectResumeInfo(Long id);

    void updateResume(Resume resume);
}
