package Services;

import Models.Show;
import Models.User;
import Utils.FileUtils;

import java.util.List;

public class ShowService implements IShowService {
    private String fileShow = "./data/Shows.txt";

    @Override
    public void createShow(Show show) {
        List<Show> showList = getAllShows();
        showList.add(show);
        FileUtils.writeData(fileShow, showList);
        System.out.println("Create show successful!");
    }

    @Override
    public List<Show> getAllShows() {
        return FileUtils.readData(fileShow, Show.class);
    }

    @Override
    public void updateShow(Show show) {

    }

    @Override
    public void deleteShow(long idShow) {

    }

    @Override
    public long nextIdShow() {
        long maxIdShow = 20000;
        List<Show> showList = getAllShows();
        for (Show s: showList){
            if (s.getIdShow() > maxIdShow){
                maxIdShow = s.getIdShow();
            }
        }
        return maxIdShow + 1;
    }
}
