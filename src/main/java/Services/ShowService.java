package Services;

import Models.Show;
import Models.User;
import Utils.FileUtils;

import java.util.List;

public class ShowService implements IModelService<Show> {
    private String fileShow = "./data/Shows.txt";

    @Override
    public void create(Show show) {
        List<Show> showList = getAll();
        showList.add(show);
        FileUtils.writeData(fileShow, showList);
        System.out.println("Create show successful!");
    }

    @Override
    public List<Show> getAll() {
        return FileUtils.readData(fileShow, Show.class);
    }

    @Override
    public void update(Show show) {

    }

    @Override
    public void delete(long idShow) {
        List<Show> showList = getAll();
        int count = 0;
        for (Show s : showList){
            if (s.getIdShow() == idShow){
                showList.remove(s);
                FileUtils.writeData(fileShow, showList);
                System.out.println("Delete show successful!");
                count++;
                break;
            }
        }
        if (count == 0) System.out.println("Cannot find id show to delete!");
    }

    @Override
    public long nextId() {
        long maxIdShow = 20000;
        List<Show> showList = getAll();
        for (Show s: showList){
            if (s.getIdShow() > maxIdShow){
                maxIdShow = s.getIdShow();
            }
        }
        return maxIdShow + 1;
    }

    @Override
    public Show findById(long id) {
        List<Show> showList = getAll();
        for (Show s: showList){
            if (s.getIdShow() == id){
                return s;
            }
        }
        return null;
    }
}
