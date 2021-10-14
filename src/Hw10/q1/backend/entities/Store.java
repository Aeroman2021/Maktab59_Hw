package Hw10.q1.backend.entities;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private Map<Integer, Integer> medicineQuantities;
    private HashMap<Integer, Medicine<Integer>> medicineList;


    public Store() {
        medicineQuantities = new HashMap<>();
        medicineList = new HashMap<>();
        putMedicine();
        initializeMedicineList();

    }

    public Map<Integer, Integer> getMedicineQuantities() {
        return medicineQuantities;
    }


    public HashMap<Integer, Medicine<Integer>> getMedicineList() {
        return medicineList;
    }

    private void initializeMedicineList() {

        Medicine<Integer> lidocaineOral = new Medicine<>(1, "lidocaine", 1, 20000d);
        Medicine<Integer> lidocaineSolidOral = new Medicine<>(2, "lidocaine", 2, 25000d);
        Medicine<Integer> lidocaineLiquidOral = new Medicine<>(3, "lidocaine", 3, 35000d);
        Medicine<Integer> MorphineInjection = new Medicine<>(4, "Morphine", 1, 20000d);
        Medicine<Integer> MorphineInTablet = new Medicine<>(5, "Morphine", 2, 15000d);
        Medicine<Integer> MorphineOralLiquid = new Medicine<>(6, "Morphine", 4, 18980d);
        Medicine<Integer> codeineTablet = new Medicine<>(7, "codeine", 1, 42000d);
        Medicine<Integer> dexamethasoneTablet = new Medicine<>(8, "dexamethasone", 1, 32000d);
        Medicine<Integer> dexamethasoneLiquidOral = new Medicine<>(9, "dexamethasone", 2, 16000d);
        Medicine<Integer> dexamethasoneInjection = new Medicine<>(10, "dexamethasone", 4, 19000d);
        Medicine<Integer> diazepamInjection = new Medicine<>(11, "diazepam", 1, 20000d);
        Medicine<Integer> diazepamTablet = new Medicine<>(12, "diazepam", 2, 26000d);
        Medicine<Integer> diazepamOralLiquid = new Medicine<>(13, "diazepam", 4, 30000d);
        Medicine<Integer> atropineInjection = new Medicine<>(14, "atropine", 4, 20000d);
        Medicine<Integer> midazolamInjection = new Medicine<>(15, "midazolam", 2, 19000d);
        Medicine<Integer> midazolamOralLiquid = new Medicine<>(16, "midazolam", 3, 17000d);
        Medicine<Integer> midazolamOralSolid = new Medicine<>(17, "midazolam", 4, 19500d);

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
    }

    private void putMedicine() {
        medicineQuantities.put(1, 50);
        medicineQuantities.put(2, 40);
        medicineQuantities.put(3, 60);
        medicineQuantities.put(4, 50);
        medicineQuantities.put(5, 70);
        medicineQuantities.put(6, 40);
        medicineQuantities.put(7, 50);
        medicineQuantities.put(8, 90);
        medicineQuantities.put(9, 70);
        medicineQuantities.put(10, 60);
        medicineQuantities.put(11, 30);
        medicineQuantities.put(12, 50);
        medicineQuantities.put(13, 60);
        medicineQuantities.put(14, 40);
        medicineQuantities.put(15, 60);
        medicineQuantities.put(16, 45);
        medicineQuantities.put(17, 55);
    }


}
