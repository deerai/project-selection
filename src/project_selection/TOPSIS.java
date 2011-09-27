/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TOPSIS.java
 *
 * Created on Sep 25, 2011, 6:49:23 PM
 */
package project_selection;

import java.util.*;
import javax.swing.table.TableColumn;
/**
 *
 * @author thymemine
 */
public class TOPSIS extends javax.swing.JFrame {
    private static Object decisiobMetrixTable;

    private static class decisionMetrixTable {

        private static int getRowCount() {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        public decisionMetrixTable() {
        }
    }
    

    /** Creates new form TOPSIS */
    public TOPSIS() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        addRowButton = new javax.swing.JButton();
        addColumnButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        TOPSIS = new javax.swing.JLabel();
        decisionMetrixTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addRowButton.setText("AddRow");

        addColumnButton.setText("AddCol");

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        TOPSIS.setText("TOPSIS Input table");

        decisionMetrixTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"A1", null, null, null},
                {"A2", null, null, null},
                {"A3", null, null, null}
            },
            new String [] {
                "", "C1", "C2", "C3"
            }
        ));
        decisionMetrixTable.setName("decisionMetrixTable"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TOPSIS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addRowButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addColumnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 165, Short.MAX_VALUE)
                    .addComponent(decisionMetrixTable, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 165, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(addRowButton)
                .addGap(18, 18, 18)
                .addComponent(addColumnButton)
                .addGap(18, 18, 18)
                .addComponent(runButton)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(TOPSIS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 126, Short.MAX_VALUE)
                    .addComponent(decisionMetrixTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 126, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
       //read data into array
       int rows = decisionMetrixTable.getRowCount();
       int columns = decisionMetrixTable.getColumnCount();
       double[] idPositiveSol = new double[columns+1];
       double[] idNegativeSol = new double[columns+1];
       double[][] scores= new double[rows][columns];
       double[] positiveSeparation = new double[rows];
       double[] negativeSeparation = new double[rows];
       double[] coefficient = new double[rows];
       double bestSol =0;
       int bestAlternative =0;
       
       for(int r=1; r<=rows; r++){
           for(int c=1; c<=columns; c++){
               scores[r][c]=Double.parseDouble(decisionMetrixTable.getValueAt(r,c).toString());
           }
       }
       idPositiveSol = identifyPositiveIdealSolution(scores, columns, rows);
       idNegativeSol = identifyNegativeIdealSolution(scores, columns, rows);
       positiveSeparation = calSeparation(scores, idPositiveSol, columns, rows);
       negativeSeparation = calSeparation(scores, idPositiveSol, columns, rows);
       coefficient = calCoefficient(positiveSeparation, negativeSeparation, rows);
       //look for best coefficient
       for(int i=1; i<=rows; i++){
           if(coefficient[i]>bestSol){
               bestSol=coefficient[i];
               bestAlternative = i;
           }
          System.out.print("best alternative is " + bestAlternative + " with coeffient " + bestSol);     
       }
      
    }//GEN-LAST:event_runButtonActionPerformed
   //call identifyPossitiveIdealSolution
    private double[] identifyPositiveIdealSolution(double[][] scores, int columns, int rows){
           
           double[] positiveIDS = new double[columns+1];
           for ( int c=1; c<= columns; c++ ){
               double max = 0;
               for ( int r=1; r<=rows; r++ ){
                   if(scores[r][c]>max)
                       max=scores[r][c];
               }
               positiveIDS[c]=max;
           }
           return positiveIDS; 
       }
    private double[] identifyNegativeIdealSolution(double[][] scores, int columns, int rows){
        
        double[] negativeIDS = new double[columns+1];
        for( int c=1; c<=columns; c++){
            double min = 1;
            for (int r=1; r<=rows; r++){
                if(scores[r][c]<min)
                    min=scores[r][c];
            }
            negativeIDS[c]=min;
        }
        return negativeIDS;
    }
    //calculate both possitive and negative
    private double[] calSeparation(double[][] scores, double[] idealSolution, int columns, int rows){
        double[] Separation = new double[rows+1];
       
        for(int r=1; r<=rows; r++){
            
            double[] sumation= new double[rows];
            sumation[r]=0;
            double[] subtraction = new double[rows];
            
            for (int c=1; c<=columns; c++){
                double sbttn=0;
                subtraction[c]= Math.abs(scores[r][c]-idealSolution[c]);
                sbttn=subtraction[c];
                sumation[r]= sumation[r] + Math.pow(sbttn, 2);
            }
            Separation[r]=Math.sqrt(sumation[r]);
        }
        return Separation;
            
    }
    private double[] calCoefficient(double[] positiveSeparation, double[] negativeSeparation, int rows){
        double[] coefficient = new double[rows+1];
        for(int r=1; r<=rows; r++){
            coefficient[r] = negativeSeparation[r]/(negativeSeparation[r]+positiveSeparation[r]);
        } 
        
        return coefficient;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TOPSIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TOPSIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TOPSIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TOPSIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //create metrix (vector) which will be fed value from table in
        
                
            
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TOPSIS().setVisible(true);
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TOPSIS;
    private javax.swing.JButton addColumnButton;
    private javax.swing.JButton addRowButton;
    private javax.swing.JTable decisionMetrixTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton runButton;
    // End of variables declaration//GEN-END:variables
}
