package com.zodiacscope.zenith.astroverse.Model;

import java.util.List;

public class Festival_Response {
    private List<IndiaFestival> india_fastival;

    public List<IndiaFestival> getIndiaFastival() {
        return india_fastival;
    }

    public void setIndiaFastival(List<IndiaFestival> india_fastival) {
        this.india_fastival = india_fastival;
    }

    public static class IndiaFestival {
        private String id;
        private String year;
        private String Date;
        private String Weekday;
        private String Name;
        private String Notes;
        private String entrydate;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getWeekday() {
            return Weekday;
        }

        public void setWeekday(String weekday) {
            Weekday = weekday;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getNotes() {
            return Notes;
        }

        public void setNotes(String notes) {
            Notes = notes;
        }

        public String getEntrydate() {
            return entrydate;
        }

        public void setEntrydate(String entrydate) {
            this.entrydate = entrydate;
        }
    }
}
