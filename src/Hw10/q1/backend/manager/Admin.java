package Hw10.q1.backend.manager;

import Hw10.q1.backend.dao.MedicineDao;
import Hw10.q1.backend.dao.PatientDao;
import Hw10.q1.backend.dao.PrescriptionDao;
import Hw10.q1.backend.entities.Patient;
import Hw10.q1.backend.entities.PrescriptionItems;
import hw8.q4.backend.exceptions.ManagerException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    private List<Patient> patientList;
    private PatientDao patientDao;
    private PrescriptionDao prescriptionDao;
    private MedicineDao medicineDao;


    public Admin() {
        patientDao = new PatientDao();
        patientList = new ArrayList<>();
        prescriptionDao = new PrescriptionDao();
        medicineDao = new MedicineDao();
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void Register(Patient patient) {
        try {
            patientDao.save(patient);
        } catch (SQLException e) {
            System.out.println("Error while adding the patient to the database");
        }
    }

    public void addPresItemsToPrescriptionDao(int patientId, int prescriptionId, PrescriptionItems prescriptionItems) {
        try {
            prescriptionDao.savePrescriptionItem(prescriptionItems, patientId, prescriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while adding items to the database.");
        }
    }

    public void updateItemsAtStore(int id,double itemPrice, int quantity, boolean isExist) {
        try {
            medicineDao.updateMedicineItemsAtStoreByAdmin(id,itemPrice, quantity, isExist);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while updating medicine ");
        }
    }

    public void printPrescriptionForPatient(int patientId, int prescriptionId) {
        try {
            prescriptionDao.printListOfPrescriptionForPatient(patientId, prescriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to show the prescription list");
        }
    }

    public void updateMedicineByUser(int medId,int medForm, int medQuantity) {
        try {
            prescriptionDao.updatePrescriptionItemsByPatient(medId,medForm, medQuantity);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating the medicine item ");
        }
    }


    public void updateMedicineQuantityInStore(PrescriptionItems item) throws SQLException, ManagerException {
        if (medicineDao.findMedicineItemByNameAndForm(item.getName(), item.getForm())==null)
            throw new ManagerException("The medicine is not exist");
        else
            medicineDao.updateMedicineQuantity(item.getName(), item.getQuantity(), item.getForm());
    }

    public void deleteMedicineByUser(int medId) {
        try {
            prescriptionDao.deleteByIndex(medId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while deleting the medicine item ");
        }
    }

    public void deletePrescriptionByUser(int patientId,int PrescriptionId){
        try {
            prescriptionDao.deletePrescByUser(patientId,PrescriptionId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while deleting the prescription ");
        }
    }

    public void updatePrescriptionByAdmin(int itemId,double itemPrice,boolean doesExist,boolean isConfirmed){
        try {
            prescriptionDao.updatePrescriptionItemsByAdmin(itemId,itemPrice,doesExist,isConfirmed);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating the prescription ");
        }
    }

    public void advancedItemUpdater(int patientId, int prescriptionID){
        try {
            prescriptionDao.advancedUpdater(patientId,prescriptionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void printThePrescriptionTotalPrice(int patientId,int prescriptionID){

        try {
            prescriptionDao.printThePrescriptionCost(patientId,prescriptionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void isExistAndIsConfirmedUpdater(ArrayList<PrescriptionItems> prescriptionItemList,int patientId){
        for(PrescriptionItems ps : prescriptionItemList){
            String name = ps.getName();
            Integer form = ps.getForm();
            try {
                if(medicineDao.findMedicineItemByNameAndForm(name,form)!= null)
                    prescriptionDao.confirmTheItemByAdmin(patientId,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
