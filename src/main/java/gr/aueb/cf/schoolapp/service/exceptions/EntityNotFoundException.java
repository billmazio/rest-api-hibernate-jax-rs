package gr.aueb.cf.schoolapp.service.exceptions;

public class EntityNotFoundException extends Exception{
    private static final long serialVersionUID = 123456L;

    public EntityNotFoundException(Class<?> clazz, Long id) {
        super("Entity " + clazz.getSimpleName() + " with id " + id + " does not exist");

    }
}
