/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ai.mufufu.filereader;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author mufufu
 */
    public class StockBar {

        private Date date;
        private float open;
        private float high;
        private float low;
        private float close;
        private long volume;
        private float dividends;
        private float splits;


    public StockBar(Date date, float open, float high, float low, float close, long volume, float dividends, float splits) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.dividends = dividends;
        this.splits = splits;
    }
        


        /**
         * @return the open
         */
        public float getOpen() {
            return open;
        }

        /**
         * @param open the open to set
         */
        public void setOpen(float open) {
            this.open = open;
        }

        /**
         * @return the high
         */
        public float getHigh() {
            return high;
        }

        /**
         * @param high the high to set
         */
        public void setHigh(float high) {
            this.high = high;
        }

        /**
         * @return the low
         */
        public float getLow() {
            return low;
        }

        /**
         * @param low the low to set
         */
        public void setLow(float low) {
            this.low = low;
        }

        /**
         * @return the close
         */
        public float getClose() {
            return close;
        }

        /**
         * @param close the close to set
         */
        public void setClose(float close) {
            this.close = close;
        }

        /**
         * @return the volume
         */
        public long getVolume() {
            return volume;
        }

        /**
         * @param volume the volume to set
         */
        public void setVolume(long volume) {
            this.volume = volume;
        }

        /**
         * @return the dividends
         */
        public float getDividends() {
            return dividends;
        }

        /**
         * @param dividends the dividends to set
         */
        public void setDividends(float dividends) {
            this.dividends = dividends;
        }

        /**
         * @return the splits
         */
        public float getSplits() {
            return splits;
        }

        /**
         * @param splits the splits to set
         */
        public void setSplits(float splits) {
            this.splits = splits;
        }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }



        
    }