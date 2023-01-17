package DAO;

import java.util.List;

public interface EntityDAO<ID, Entity> {
    boolean create(Entity entity) throws DAOException;

   Entity getByID (ID id);

   boolean update(Entity entity);

   void delete(ID entity);

    List<Entity> getAll()throws DAOException;

}
