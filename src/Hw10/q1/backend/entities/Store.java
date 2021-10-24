package Hw10.q1.backend.entities;

import java.util.HashMap;

public class Store {

    private static HashMap<Integer, Medicine> medicineList;


    public Store() {
//        medicineList = initializeMedicineList();
    }


    public HashMap<Integer, Medicine> getMedicineList() {
        return medicineList;
    }

    private static HashMap<Integer, Medicine> initializeMedicineList() {

        Medicine lidocaineOral = new Medicine(1, "lidocaine", 1, 20000d, 50, true);
        Medicine lidocaineSolidOral = new Medicine(2, "lidocaine", 2, 25000d, 40, true);
        Medicine lidocaineLiquidOral = new Medicine(3, "lidocaine", 3, 35000d, 60, true);
        Medicine MorphineInjection = new Medicine(4, "Morphine", 1, 20000d, 50, true);
        Medicine MorphineInTablet = new Medicine(5, "Morphine", 2, 15000d, 70, true);
        Medicine MorphineOralLiquid = new Medicine(6, "Morphine", 4, 18980d, 40, true);
        Medicine codeineTablet = new Medicine(7, "codeine", 1, 42000d, 50, true);
        Medicine dexamethasoneTablet = new Medicine(8, "dexamethasone", 1, 32000d, 30, true);
        Medicine dexamethasoneLiquidOral = new Medicine(9, "dexamethasone", 2, 16000d, 90, true);
        Medicine dexamethasoneInjection = new Medicine(10, "dexamethasone", 4, 19000d, 70, true);
        Medicine diazepamInjection = new Medicine(11, "diazepam", 1, 20000d, 60, true);
        Medicine diazepamTablet = new Medicine(12, "diazepam", 2, 26000d, 30, true);
        Medicine diazepamOralLiquid = new Medicine(13, "diazepam", 4, 30000d, 50, true);
        Medicine atropineInjection = new Medicine(14, "atropine", 4, 20000d, 60, true);
        Medicine midazolamInjection = new Medicine(15, "midazolam", 2, 19000d, 40, true);
        Medicine midazolamOralLiquid = new Medicine(16, "midazolam", 3, 17000d, 50, true);
        Medicine midazolamOralSolid = new Medicine(17, "midazolam", 4, 19500d, 60, true);


        medicineList.put(1, lidocaineOral);
        medicineList.put(2, lidocaineSolidOral);
        medicineList.put(3, lidocaineLiquidOral);
        medicineList.put(4, MorphineInjection);
        medicineList.put(5, MorphineInTablet);
        medicineList.put(6, MorphineOralLiquid);
        medicineList.put(7, codeineTablet);
        medicineList.put(8, dexamethasoneTablet);
        medicineList.put(9, dexamethasoneLiquidOral);
        medicineList.put(10, dexamethasoneInjection);
        medicineList.put(11, diazepamInjection);
        medicineList.put(12, diazepamTablet);
        medicineList.put(13, diazepamOralLiquid);
        medicineList.put(14, atropineInjection);
        medicineList.put(15, midazolamInjection);
        medicineList.put(16, midazolamOralLiquid);
        medicineList.put(17, midazolamOralSolid);

        return medicineList;
    }


}
