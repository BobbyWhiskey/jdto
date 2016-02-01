# jdto
Annotation based DTO generator for Java


Welcome to the jDTO wiki!

jDTO is an annotation based java code generator for DTOs or similar classes.

For examples on how to use the code see project jdto-exemples

Here is a usage example, we have this entity class like this:


@Entity
@Table(name = "ROOM")
public class ExampleObject {
	 private int number;

	 private Date date;

	 private Collection<String> listOfStrings;
	 private Collection<Integer> listOfInts;
	 private Collection<Date> listOfDates;

         // more methods..
}
We add the annotations:

@GenerateDto
@Entity
@Table(name = "ROOM")
public class ExampleObject {

	 @DtoField
	 private int number;
	 
	 @DtoField
	 private Date date;
	 
	 @DtoField
	 private Collection<String> listOfStrings;
	 
	 @DtoField
	 private Collection<Integer> listOfInts;
	 
	 @DtoField
	 private Collection<Date> listOfDates;
}

The jDTO engine will generate this:

import java.sql.Date;
import java.util.Collection;

public class ExampleObjectDTO {

    public int number;
    public Date date;
    public Collection<String> listOfStrings;
    public Collection<Integer> listOfInts;
    public Collection<Date> listOfDates;

    int getNumber() {
        return number;
    }

    int setNumber(int number) {
        this.number = number
    }

    Date getDate() {
        return date;
    }

    Date setDate(Date date) {
        this.date = date
    }

    Collection<String> getListOfStrings() {
        return listOfStrings;
    }

    Collection<String> setListOfStrings(Collection<String> listOfStrings) {
        this.listOfStrings = listOfStrings
    }

    Collection<Integer> getListOfInts() {
        return listOfInts;
    }

    Collection<Integer> setListOfInts(Collection<Integer> listOfInts) {
        this.listOfInts = listOfInts
    }

    Collection<Date> getListOfDates() {
        return listOfDates;
    }

    Collection<Date> setListOfDates(Collection<Date> listOfDates) {
        this.listOfDates = listOfDates
    }

}

