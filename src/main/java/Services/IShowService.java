package Services;

import Models.Show;

import java.util.List;

public interface IShowService {
    void createShow(Show show);
    List<Show> getAllShows();
    void updateShow(Show show);
    void deleteShow(long idShow);
    public long nextIdShow();
}
