
package topsis;

public class TopsisAlgothm{

// TOPSIS Algorithm

    public static void main(String[] args){
        
        double[][] scores= new double[3+1][3+1];
        // dummy value
        scores[1][1] =0.01; scores[1][2] =0.03; scores[1][3] =0.05;
        scores[2][1] =0.03; scores[2][2] =0.04; scores[2][3] =0.01;
        scores[3][1] =0.05; scores[3][2] =0.02; scores[3][3] =0.04;
        
        //double positiveIdealSolution[] = new double[3];
        //double positiveIdealSolution[] = identifyPositiveIdealSolution(scores);
    //}
     //new method---------'positive ideal solution'-----------------------------
     //public double[] identifyPositiveIdealSolution(double scores[][]){
         double[] positiveIdealSolution = new double[3+1];
         for (int columns=1; columns<=3; columns++){
             double max = 0;
             for (int rows=1; rows<=3; rows++){
                 if(scores[rows][columns]>max)
                     max=scores[rows][columns];
             }
             positiveIdealSolution[columns]=max;
             }
         //return positiveIdealSolution;
     //new method---------'negative ideal solution'-----------------------------
         double[] negativeIdealSolution = new double[3+1];
         for(int columns=1; columns<=3; columns++){
             double min = 1;
             for(int rows=1; rows<=3; rows++){
                 if(scores[rows][columns]<min)
                     min=scores[rows][columns];
             }
             negativeIdealSolution[columns]=min;
         }
     //new method---------'positive distance'-----------------------------------
        double[][] positiveDistance = new double[3+1][3+1];
        double[] sPlus = new double[3+1];
        for(int rows=1; rows<=3; rows++){
            sPlus[rows]=0;
            for(int columns=1; columns<=3; columns++){
                positiveDistance[rows][columns] = Math.sqrt(Math.pow(scores[rows][columns]-positiveIdealSolution[columns],2));
                sPlus[rows]=sPlus[rows]+positiveDistance[rows][columns];
            }
        }
       
        
     //new method-------'negative distance'-------------------------------------
        double[][] negativeDistance = new double[3+1][3+1];
        double[] sMinus = new double[3+1];
        for(int rows=1; rows<=3; rows++){
            sMinus[rows] = 0;
            for(int columns=1; columns<=3; columns++){
                negativeDistance[rows][columns] = Math.sqrt(Math.pow(scores[rows][columns]-negativeIdealSolution[columns],2));
                sMinus[rows]=sMinus[rows]+negativeDistance[rows][columns];
            }
        }
     //new method--------'select the best criteria'-----------------------------
        double[] criteriaScore= new double[3+1];
        double max=0;
        int position=0; 
        for(int rows=1; rows<=3; rows++){
            criteriaScore[rows] = sMinus[rows]/(sMinus[rows]+sPlus[rows]);
            if(max<criteriaScore[rows])
                max = criteriaScore[rows];
                position=rows;
                System.out.println(" criteria = "+ position +" Score = "+criteriaScore[rows]);
        }
        System.out.println("best criteria = " + position + " Score = "+max);
        
    }
}