package dao;

import model.Image;
import java.util.List;

public interface ImageDAO {
    boolean insert(Image img);
    List<Image> selectByUserId(int userId);
}
