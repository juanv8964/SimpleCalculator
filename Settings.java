public class Settings {
    private int precision = 2;
    public int getPrecision()
    {
        return precision;
    }
    public void setPrecision(int precisionNumber)
    {
        if(precisionNumber < 0 || precisionNumber > 4){
            throw new IllegalArgumentException("Precision must be 0-4.");
        }
        this.precision = precisionNumber;
    }
    public String getFormatPattern(){
        return "%." + precision + "f";
    }
}
