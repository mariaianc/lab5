package Repo;

import DOMAIN.Entity;

public interface InterfaceCakes {

    public void add(Entity e);
    public void remove(int id);
    public void modify(Entity o, Entity n);
    public Entity get(int id);

}
