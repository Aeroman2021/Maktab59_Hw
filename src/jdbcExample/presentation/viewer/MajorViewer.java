package jdbcExample.presentation.viewer;

import jdbcExample.entity.Major;
import jdbcExample.service.MajorService;
import jdbcExample.utility.Input;

public class MajorViewer {
    MajorService majorService;

    public MajorViewer() {
        majorService = new MajorService();
    }

    public void addMajor(){
        int id = Input.getInputValue("Enter major id");
        String name = Input.getStringInputValue("Enter The major name");
        majorService.save(new Major(id,name));
    }


    public void deleteMajor(){
        int id = Input.getInputValue("Enter major id");
        majorService.deleteById(id);
    }

    public void loadMajorById(){
        int id = Input.getInputValue("Enter major id");
        majorService.loadById(id);
    }

    public void loadAllMajors(){
        for (Major major : majorService.getBaseDao().loadAll()) {
            System.out.println(major);
        }
    }

    public void printAllMajorsInformation(){
        majorService.printMajorInformation();
    }

}
