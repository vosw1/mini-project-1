package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompRepository {
    private final EntityManager em;


    public List<CompResponse.ScoutListDTO> scoutList(){
        String q = """
                SELECT rt.name, rt.resume_title, ut.age, ut.address, rt.career
                FROM user_tb ut
                INNER JOIN (
                select * 
                from resume_tb
                where id in (
                select max(id)
                from resume_tb
                group by user_id
                )
                ) rt ON ut.id = rt.user_id
                """;

        Query query = em.createNativeQuery(q);

       JpaResultMapper rm = new JpaResultMapper();
       List<CompResponse.ScoutListDTO> results = rm.list(query, CompResponse.ScoutListDTO.class);
        System.out.println(results);

        return results;
    }

    public List<Jobopen> findAll() {
        String q = """
                select * from jobopen_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
    }

    public void findById() {
        return;
    }

    @Transactional
    public void save() {
        return;
    }

    @Transactional
    public void upDate() {
        return;
    }

    @Transactional
    public void delete() {
        return;
    }
}
