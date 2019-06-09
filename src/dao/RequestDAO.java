package dao;

import model.Request;

public interface RequestDAO {
    boolean insert(Request req);
    Request selectByRequestId(int id);

}
