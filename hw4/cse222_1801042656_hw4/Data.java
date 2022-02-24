package cse222_1801042656_hw4;

public class Data<E>{
    private E value;
    private int numberOfOccurences;
    private String notation;

    public Data(E value, int numberOfOccurences){
        this.value = value;
        this.numberOfOccurences = numberOfOccurences;
        notation = value + "," + numberOfOccurences;
    }

    public Data(E value){
        this.value = value;
        numberOfOccurences = 1;
        notation = value + "," + numberOfOccurences;
    }

    public E getValue(){
        return value;
    }

    public void setValue(E value, int numberOfOccurences){
        this.value = value;
        this.numberOfOccurences = numberOfOccurences;
        notation = value + "," + numberOfOccurences;
    }

    public int getNumberOfOccurences(){
        return numberOfOccurences;
    }

    public int setNumberOfOccurences(int i){
        this.numberOfOccurences = i; 
        return numberOfOccurences;
    }

    public int incrementNumberOfOccurences(){
        numberOfOccurences++;
        setNotation();
        return numberOfOccurences;
    }

    public int decrementNumberOfOccurences(){
        numberOfOccurences--;
        setNotation();
        return numberOfOccurences;
    }

    public void setNotation(){
        notation = value + "," + numberOfOccurences;
    }

    public String getNotaton(){
        return notation;
    }

    @Override
    public String toString() {
        return notation;
    }

    

}
