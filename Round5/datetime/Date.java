class Date {

    private int year;
    private int month;
    private int day;
    private String date;

    Date(int year, int month, int day) throws DateException
    {
        int illegal = 0;
        if(day<1 || 31 < day){illegal = 1;}
        if(month<1 || 12 < month){illegal = 1;}
        if(year<1){illegal = 1;}

        if(illegal == 1){
            throw new DateException(String.format("Illegal date %d.%d.%d", day, month, year));
        }
        else{
            this.day = day;
            this.month = month;
            this.year = year;

            String sDay =  Integer.toString(day);
            if(sDay.length() == 1) {sDay = ("0" + sDay);}
            String sMonth = Integer.toString(month);
            if(sMonth.length() == 1) {sMonth = ("0" + sMonth);}
            String sYear = Integer.toString(year);
            this.date = (sDay + "." + sMonth + "." + sYear);
        }
    }

    public Integer getYear(){return year;}
    public Integer getMonth(){return month;}
    public Integer getDay(){return day;}
    public String toString(){return this.date;}
}
