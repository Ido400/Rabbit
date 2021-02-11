import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSV {
    private String path;
    private ArrayList<String[]> vaccineList = new ArrayList<String[]>();
    private ArrayList<String[]> vaccineList50 = new ArrayList<String[]>();
    private int avgRisk;

    public CSV(String path){
        this.path = path;
    }
    public void CreateCSVFile(File file) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    }
    
    public void CheckPremmisonToVaccine(){

    }

    public void AddWorkerToVaccineList(String[] Worker) throws Exception{
        vaccineList.add(Worker);
    }
    
    public String Create50PersonList(){
        CalculateAvgRisk();
        int count = 0;
        int MaxWorkerRisk = 0;
        String[] MaxWorker = new String[this.vaccineList.size()];
        
        for (String[] worker : this.vaccineList) {
            if(CheckWorkerRisk_VS_AVG(worker[4])){
                this.vaccineList50.add(worker);
                this.vaccineList.remove(count);
            }
            else if(MaxWorkerRisk < Integer.parseInt(worker[4])){
                MaxWorkerRisk = Integer.parseInt(worker[4]);
            } 


            count++;
        }

        if(this.vaccineList50.size() == 50)
            return this.vaccineList.toString();
        else if(this.vaccineList50.size() < 50)

    }

    public boolean CheckWorkerRisk_VS_AVG(String riskPrecent){
        if(this.avgRisk >= Integer.parseInt(riskPrecent)){
            return true;
        }
        return false;
    }

    public void CalculateAvgRisk(){
        for (String[] worker : this.vaccineList) {
            this.avgRisk = this.avgRisk +  Integer.parseInt(worker[4]);
        }
        this.avgRisk = this.avgRisk / this.vaccineList.size();
    }

    public void WriteToCSVFile(String text){

        CheckFileExsist();
        
        try(PrintWriter pw = new PrintWriter(new File(this.path)); )
        {
            pw.write(text);
        }
        catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
    }

    public void CheckFileExsist(){
        File file = new File(this.path);

        if(!file.exists()){
            try{
            CreateCSVFile(file);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
