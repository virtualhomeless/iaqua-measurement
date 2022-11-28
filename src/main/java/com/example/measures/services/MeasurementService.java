package com.example.measures.services;


import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.example.measures.model.Measurement;
import com.example.measures.repository.MeasurementDAO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementDAO measurementDAO;
    //post
    public Measurement addMeasurement(Measurement newMeasurement){
        return measurementDAO.save(newMeasurement);
    }
    //get
    public List<Measurement> getAllMeasurementsOfEntireSystem(){
        return measurementDAO.findAll();
    }
    public Measurement getMeasurementById(Long measurementId){
        if(measurementDAO.findById(measurementId).isPresent()){ //this just valide a not-null object 'Measurement', but 'findById' is still deprecated... can be replaced for something else?
            return measurementDAO.getReferenceById(measurementId);
        }else{
            return null; //can this throw an exception?
        }
    }
    public List<Measurement> getAllMeasurementsByStatus(@NotNull String measurementStatus){
        List<Measurement> allMeasurements=getAllMeasurementsOfEntireSystem();
        List<Measurement> measurementsByStatus=new ArrayList<>();
        switch(measurementStatus){
            case "OK":
                for (int i = 0; i < allMeasurements.size(); i++) {
                    if(allMeasurements.get(i).getMseStatus().name().equals("OK")){
                        measurementsByStatus.add(allMeasurements.get(i));
                    }
                }
                break;
            case "ALTERADO":
                for (int i = 0; i < allMeasurements.size(); i++) {
                    if(allMeasurements.get(i).getMseStatus().name().equals("ALTERADO")){
                        measurementsByStatus.add(allMeasurements.get(i));
                    }
                }
                break;
        }
        return measurementsByStatus;
    }
    public List<Measurement> getAllMeasurementsByOperator(Long operatorIdLookingFor){
        List<Measurement> allMeasurements=getAllMeasurementsOfEntireSystem();
        List<Measurement> measurementsByOperator=new ArrayList<>();
        for (int i = 0; i < allMeasurements.size(); i++) {
            if(allMeasurements.get(i).getMseUserWhoSendId()==operatorIdLookingFor){
                measurementsByOperator.add(allMeasurements.get(i));
            }
        }
        return measurementsByOperator;
    }
    public List<Measurement> getAllMeasurementsByPlant(Long plantIdLookingFor){
        List<Measurement> allMeasurements=getAllMeasurementsOfEntireSystem();
        List<Measurement> measurementsByPlant=new ArrayList<>();
        for (int i = 0; i < allMeasurements.size(); i++) {
            if(allMeasurements.get(i).getMsePlantWhereSendId()==plantIdLookingFor){
                measurementsByPlant.add(allMeasurements.get(i));
            }
        }
        return measurementsByPlant;
    }




    //excel
    public void getExcel() throws Exception {

        // Inicializar un objeto de libro de trabajo
        Workbook workbook = new Workbook();

        // Obtención de la referencia de la hoja de cálculo
        Worksheet worksheet = workbook.getWorksheets().get(0);

        // Crear una instancia de un objeto ArrayList
        ArrayList<String> list = new ArrayList<String>();
        // Agregue algunos nombres a la lista como valores de cadena
        list.add("ID");
        list.add("Fecha");
        list.add("Observacion");
        list.add("Foto");
        list.add("Estado");
        list.add("Operario");
        list.add("Planta");
        list.add("Cloro");
        list.add("Fluor");
        list.add("PH");
        list.add("Sedimento");
        list.add("Magnesio");
        list.add("Fierro");

        // Exportar el contenido de ArrayList verticalmente en la primera fila y la primera columna de la hoja de trabajo.
        worksheet.getCells().importArrayList(list, 0, 0, false);

        if(getAllMeasurementsOfEntireSystem().size()>0){
            for (int i = 0; i < getAllMeasurementsOfEntireSystem().size(); i++) {
                list.clear();
                list.add(String.valueOf(getAllMeasurementsOfEntireSystem().get(i).getMseId()));
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseDateAndHour());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseObservation());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMsePhotography());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseStatus().name());
                list.add(String.valueOf(getAllMeasurementsOfEntireSystem().get(i).getMseUserWhoSendId()));
                list.add(String.valueOf(getAllMeasurementsOfEntireSystem().get(i).getMsePlantWhereSendId()));
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseChlorineAmount());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseFluorineAmount());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMsePhAmount());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseSedimentAmount());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseMagnesiumAmount());
                list.add(getAllMeasurementsOfEntireSystem().get(i).getMseIronAmount());
                worksheet.getCells().importArrayList(list, i+1, 0, false);
            }
        }
        // Guardar el archivo de Excel
        workbook.save("C:\\Users\\alexs\\OneDrive\\Escritorio\\contenedore-SpringBoot\\contenedores-clonados\\Output.xlsx");
    }



}











