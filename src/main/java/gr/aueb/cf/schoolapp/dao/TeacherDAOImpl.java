package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;
import java.util.List;


@Provider
@ApplicationScoped
public class TeacherDAOImpl implements ITeacherDAO{
    @Override
    public Teacher insert(Teacher teacher) {
        EntityManager em = getEntityManager();
        em.persist(teacher);
        return teacher;
    }

    @Override
    public Teacher update(Teacher teacher) {
        getEntityManager().merge(teacher);
        return teacher;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Teacher teacherToDelete = em.find(Teacher.class,id);
        em.remove(teacherToDelete);

    }

    @Override
    public List<Teacher> getByLastname(String lastname) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Teacher> selectQuery = builder.createQuery(Teacher.class);
        Root<Teacher> teacherRoot = selectQuery.from(Teacher.class);
        ParameterExpression<String> tLastname = builder.parameter(String.class);
        selectQuery.select(teacherRoot).where(builder.like(teacherRoot.get("lastname"),tLastname));
        TypedQuery<Teacher> query = getEntityManager().createQuery(selectQuery);
        query.setParameter(tLastname,lastname + "%");

        return query.getResultList();
    }

    @Override
    public Teacher getById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Teacher.class, id);
    }
    private EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
