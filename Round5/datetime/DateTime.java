class DateTime extends Date{

    private int hour;
    private int minute;
    private int second;
    private String sTime;

    DateTime(int year, int month, int day, int hour, int minute, int second) 
    throws DateException{
        
        super(year, month, day);

        int illegal = 0;
        if(hour < 0 || 23 < hour) {illegal = 1;}
        if(minute < 0 || 59 < minute){illegal = 1;}
        if(second < 0 || 59 < second){illegal = 1;}

        if(illegal == 1){
            throw new DateException(String.format("Illegal time %d:%d:%d", hour, minute, second));
        }
        else{
            this.hour = hour;
            this.minute = minute;
            this.second = second;

            String sHour = Integer.toString(hour);
            if(sHour.length() == 1) {sHour = ("0" + sHour);}
            String sMinute = Integer.toString(minute);
            if(sMinute.length() == 1) {sMinute = ("0" + sMinute);}
            String sSecond = Integer.toString(second);
            if(sSecond.length() == 1) {sSecond = ("0" + sSecond);}
            sTime = (sHour + ":" + sMinute + ":" + sSecond);
        }
    }

    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public int getSecond(){return second;}
    public String toString(){
        String date = super.toString();
        String timeDate = date + " " + sTime;
        return timeDate;
    }
}
