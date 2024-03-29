package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.resume.ResumeRequest;
import com.example.jobala.skill.Skill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobopenRepository {
    private final EntityManager em;

    public void findBySkillId() {
        String q = """
                select name from skill_tb 
                """;
        Query query = em.createNativeQuery(q, Skill.class);
        List<Object[]> skills = (List<Object[]>) query.getResultList();
//
//        for (Object skill : skills) {
//            String name = (String) skill[0];
//
//            return ;
//        }
    }

    public Jobopen findByJobOpenId(int id) {
        String q = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        Jobopen jobopen = (Jobopen) query.getSingleResult();
        return jobopen;
    }

    @Transactional
    public void save(JobopenRequest.JobopenSaveDTO reqDTO, User sessionUser) {
        // jobopen인설트
        String q = """
                insert into jobopen_tb(user_id, edu, career, job_type, salary, hope_job ,comp_location ,content , end_time , jobopen_title, created_at, role) values (?,?,?,?,?,?,?,?,?,?,now(),?)
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, sessionUser.getId());
        query.setParameter(2, reqDTO.getEdu());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getJobType());
        query.setParameter(5, reqDTO.getSalary());
        query.setParameter(6, reqDTO.getHopeJob());
        query.setParameter(7, reqDTO.getCompLocation());
        query.setParameter(8, reqDTO.getContent());
        query.setParameter(9, reqDTO.getEndTime());
        query.setParameter(10, reqDTO.getJobopenTitle());
        query.setParameter(11, sessionUser.getRole());
        query.executeUpdate();

        // jobopen id 받기
        String q2 = """
                select max(id) from jobopen_tb
                """;
        Query query2 = em.createNativeQuery(q2);
        Integer jobopenId = (Integer) query2.getSingleResult();

        //스킬 인서트
        String q3 = """
                insert into skill_tb(user_id, resume_id, jobopen_id, name, role) values (?,?,?,?,?)
                """;
        Query query3 = em.createNativeQuery(q3);
        query3.setParameter(1, sessionUser.getId());
        query3.setParameter(2, null);
        query3.setParameter(3, jobopenId);
        query3.setParameter(4, reqDTO.getSkills());
        query3.setParameter(5, sessionUser.getRole());
        query3.executeUpdate();

    }


    @Transactional
    public void upDate() {
        return;
    }


    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from jobopen_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Jobopen findByIdWithUser(int id) {
        String a = """
                select * from jobopen_tb where id =?
                """;
        Query query = em.createNativeQuery(a,Jobopen.class);
        query.setParameter(1, id);
        try {
            Jobopen jobopen  = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return  null;
        }
    }

    public Jobopen findById(Integer id) {
        Query query = em.createNativeQuery("select * from jobopen_tb where id = ?", Jobopen.class);
        query.setParameter(1, id);

        try {
            Jobopen jobopen = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void update(Jobopen jobopenId, JobopenRequest.UpdateDTO reqDTO) {
        String a = """
                update jobopen_tb set compname = ? ,jobopenTitle=? , career=?, edu=?, jobType=?,salary=?,compLocation=?,content=? ,skills =? where id=?
                """;
        Query query = em.createNativeQuery(a);
        query.setParameter(1, reqDTO.getCompname());
        query.setParameter(2, reqDTO.getJobopenTitle());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getEdu());
        query.setParameter(5, reqDTO.getJobType());
        query.setParameter(6, reqDTO.getSalary());
        query.setParameter(7, reqDTO.getCompLocation());
        query.setParameter(8, reqDTO.getContent());
        query.setParameter(9, reqDTO.getSkills());
        query.setParameter(10, jobopenId);
        query.executeUpdate();
    }

}
