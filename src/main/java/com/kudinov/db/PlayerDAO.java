package com.kudinov.db;

import java.util.List;

public interface PlayerDAO {

    public void save(Player player);

    public void delete(Player player);

    public List<Player> getAll();

    public Player getById(Integer id);

}
