/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ai.mufufu.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mufufu
 */
public class Filereader {

    public static void main(String[] args) {
        


        
        
        
        System.out.println("Hello World!");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<StockBar> bars = new ArrayList<>();
        
        String path = "/home/mufufu/Downloads/data/1h/VZ.csv";
        //String path= "/Users/matiastames/Downloads/AAPL.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                //StockBar(LocalDate date, float open, float high, float low, float close, long volume, float dividends, float splits)
                bars.add(
                    new StockBar(
                            format.parse(values[0].substring(0, 19)), 
                            Float.parseFloat(values[1]),
                            Float.parseFloat(values[2]),
                            Float.parseFloat(values[3]),
                            Float.parseFloat(values[4]),
                            Long.parseLong(values[5]),
                            Float.parseFloat(values[6]),
                            Float.parseFloat(values[7])
                    )
                );
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {        
            Logger.getLogger(Filereader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        //System.out.println(String.format("Starting with Funds: $ %f", funds));
        
        for (int n=50; n<=2000; n=n+10) {

        
        // intenta 70!!!!
        int c=1;
        float sum=0;

        float ema=0;
        float ema_prev=0;
        float wm;
        
        wm = 2f/(n+1f);
        //wm = 0.039215686f;
        
        List<StockBar> mal = new ArrayList<>();
        
        ema_prev = bars.get(0).getClose();
        
        

        for (int i=0; i<n; i++) {
            sum = sum + (bars.get(i)).getClose();
        }
        ema_prev = sum/n;
        
        

        int crossovers=0;
        float funds=100000;
        float position=0;
        float pvalue=funds;
        int long_pos=0;
        float prev_close=0;            

            for (StockBar b : bars) {

                if (c > n) {
                    ema = ((b.getClose()- ema_prev)*wm)+ema_prev;
                    
                    prev_close=0;


                    if (c>=1) {
                        prev_close = bars.get(c-2).getClose();

                    }

                    /*
                    System.out.println(String.format("Date: %s\tClose: %f\tn: %d\tEMA_PREV: %f\tEMA: %f", 
                        b.getDate(), b.getClose(),c, ema_prev, ema));
                    */

                    pvalue = funds + (position*b.getClose());
                    
                    //System.out.println(String.format("T: %d, Date: %s, Close: %.2f, EMA: %.2f, funds: $ %,.2f, Position: %2f, Portfolio Value: %,.2f", c,b.getDate(), b.getClose(),ema,funds, position, pvalue));
                    
                    if (ema > b.getClose() && ema < prev_close) {
                        if (long_pos !=0 ) {
                        
                        //System.out.println(String.format("Crossover SELL!!!: ema: %.2f, close: %.2f, ema_prev: %.2f, prev_close: %.2f", ema, b.getClose(), ema_prev, prev_close));
                        
                        crossovers++;

                        if (position > 0.0) {
                            funds = funds + (position*b.getClose());
                            position = 0;
                            //System.out.println(String.format("Funds: %,.2f, Position: %,.2f", funds, position));
                        }                    

                        position = position + (funds/b.getClose())*-1;
                        funds = funds + (position*b.getClose())*-1;

                        /*
                        System.out.println(String.format("Position %f stocks at %f, funds: %,.2f", 
                                position, prev_close, funds));
                        */
                        long_pos=0;
                        }

                    }               
                    if (ema < b.getClose() && ema > prev_close) {
                        
                        //System.out.println(String.format("Crossover BUY!!!: ema: %f, close: %.2f, ema_prev: %.2f, prev_close: %.2f", ema, b.getClose(), ema_prev, prev_close));
                        
                        crossovers++;
                        if (long_pos == 0) {

                            if (position < 0.0) {
                                float debe = position * b.getClose();
                                
                                //System.out.println(String.format("Funds: %,.2f", funds));
                                funds = funds + debe;
                                //System.out.println(String.format("Debe: %,.2f, Quedan en funds: %,.2f", debe, funds));
                                position = 0;
                            }
                            position = position + (funds/b.getClose());
                            funds = funds + (position*b.getClose())*-1;
                            /*
                            System.out.println(String.format("Position %f stocks at %f, funds: %,.2f", 
                                    position, prev_close, funds));
                            */
                            long_pos = 1;

                        }

                    }                   

                    ema_prev=ema;
                }
                c++;
            }
             System.out.println(String.format("N: %d, Crossovers: %d, Portfolio Value: %,.2f", n,crossovers,pvalue));

        }

    }
    
    // Datetime,Open,High,Low,Close,Volume,Dividends,Stock Splits

}
